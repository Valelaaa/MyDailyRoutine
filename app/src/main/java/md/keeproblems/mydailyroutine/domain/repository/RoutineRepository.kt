package md.keeproblems.mydailyroutine.domain.repository

import md.keeproblems.mydailyroutine.domain.model.Routine

interface RoutineRepository {
    suspend fun getRoutines(): Result<List<Routine>>
    suspend fun saveRoutine(routine: Routine): Result<Unit>
}
