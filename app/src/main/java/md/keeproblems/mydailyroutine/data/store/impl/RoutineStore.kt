package md.keeproblems.mydailyroutine.data.store.impl

import md.keeproblems.mydailyroutine.data.store.DataStore
import md.keeproblems.mydailyroutine.data.store.DataStoreKey
import md.keeproblems.mydailyroutine.data.store.IRoutineStore
import md.keeproblems.mydailyroutine.data.store.serializer.JsonSerializer
import md.keeproblems.mydailyroutine.domain.model.Routine
import javax.inject.Inject

class RoutineStore @Inject constructor(
    private val dataStore: DataStore,
    private val jsonSerializer: JsonSerializer
) : IRoutineStore {
    private val key = DataStoreKey.RoutineStoreKey

    override suspend fun getAllRoutines(): List<Routine> {
        val rawValue = dataStore.get(key, String::class.java)
        if (rawValue.isNullOrEmpty()) {
            return emptyList()
        }
        val list = jsonSerializer.deserialize<List<Routine>>(rawValue) ?: emptyList()
        return list
    }

    override suspend fun getRoutine(routineId: String): Routine? {
        val currentList = getAllRoutines()
        return currentList.find { it.id == routineId }
    }

    override suspend fun putRoutine(routine: Routine) {
        val newList = getAllRoutines() + routine
        val rawList = jsonSerializer.serialize(newList)
        dataStore.put(key, rawList)
    }

    override suspend fun deleteRoutine() = dataStore.delete(key)

    override suspend fun clear() = dataStore.clear()
}
