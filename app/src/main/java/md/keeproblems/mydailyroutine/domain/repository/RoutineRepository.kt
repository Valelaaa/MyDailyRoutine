package md.keeproblems.mydailyroutine.domain.repository

import md.keeproblems.mydailyroutine.domain.model.Routine

interface RoutineRepository {
    suspend fun getRoutines(getCached: Boolean = true): Result<List<Routine>>
    suspend fun saveRoutine(routine: Routine): Result<Unit>
    suspend fun getRoutineById(routineId: String): Result<Routine?>
}
