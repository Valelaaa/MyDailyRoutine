package md.keeproblems.mydailyroutine.data.store

import md.keeproblems.mydailyroutine.domain.model.Routine

interface IRoutineStore {

    suspend fun getAllRoutines(): List<Routine>
    suspend fun getRoutine(routineId: String): Routine?
    suspend fun putRoutine(routine: Routine)
    suspend fun deleteRoutine()
    suspend fun clear()
}