package md.keeproblems.mydailyroutine.data.repository

import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import md.keeproblems.mydailyroutine.data.store.IRoutineStore
import md.keeproblems.mydailyroutine.domain.model.Routine
import md.keeproblems.mydailyroutine.domain.repository.RoutineRepository

class RoutineRepositoryImpl @Inject constructor(private val dataStore: IRoutineStore) :
    RoutineRepository {
    private val _cachedRoutine = MutableStateFlow<List<Routine>>(emptyList())

    override suspend fun getRoutines(getCached: Boolean): Result<List<Routine>> = runCatching {
        if (getCached && _cachedRoutine.value.isNotEmpty()) {
            _cachedRoutine.value
        } else {
            val routines = dataStore.getAllRoutines()
            _cachedRoutine.value = routines
            routines
        }
    }.onFailure { it.printStackTrace() }


    override suspend fun saveRoutine(routine: Routine): Result<Unit> =
        runCatching {
            dataStore.putRoutine(routine)
        }.onFailure { it.printStackTrace() }

    override suspend fun getRoutineById(routineId: String): Result<Routine?> = runCatching {
        val routines = getRoutines(getCached = true)
        routines.getOrNull()?.find { it.id == routineId }
    }.onFailure { it.printStackTrace() }
}