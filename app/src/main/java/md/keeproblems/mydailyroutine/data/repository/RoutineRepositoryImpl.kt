package md.keeproblems.mydailyroutine.data.repository

import jakarta.inject.Inject
import md.keeproblems.mydailyroutine.data.store.IRoutineStore
import md.keeproblems.mydailyroutine.domain.model.Routine
import md.keeproblems.mydailyroutine.domain.repository.RoutineRepository

class RoutineRepositoryImpl @Inject constructor(private val dataStore: IRoutineStore) :
    RoutineRepository {
    override suspend fun getRoutines(): Result<List<Routine>> = runCatching {
            dataStore.getAllRoutines()
    }.onFailure { it.printStackTrace() }

    override suspend fun saveRoutine(routine: Routine): Result<Unit> =
        runCatching {
            dataStore.putRoutine(routine)
        }.onFailure { it.printStackTrace() }
}