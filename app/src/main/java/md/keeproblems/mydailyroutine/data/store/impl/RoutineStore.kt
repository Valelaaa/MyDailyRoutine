package md.keeproblems.mydailyroutine.data.store.impl

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jakarta.inject.Inject
import md.keeproblems.mydailyroutine.data.store.DataStore
import md.keeproblems.mydailyroutine.data.store.DataStoreKey
import md.keeproblems.mydailyroutine.data.store.IRoutineStore
import md.keeproblems.mydailyroutine.domain.model.Routine

class RoutineStore @Inject constructor(
    private val dataStore: DataStore,
    private val gson: Gson
) : IRoutineStore {
    private val key = DataStoreKey.RoutineStoreKey

    override suspend fun getAllRoutines(): List<Routine> {
        val rawValue = dataStore.get(key, String::class.java)
        if (rawValue.isNullOrEmpty()) {
            return emptyList()
        }
        val type = object : TypeToken<List<Routine>>() {}.type
        val list: List<Routine> = gson.fromJson(rawValue, type)
        return list
    }

    override suspend fun getRoutine(routineId: String): Routine? {
        val currentList = getAllRoutines()
        return currentList.find { it.id == routineId }
    }

    override suspend fun putRoutine(routine: Routine) {
        val newList = getAllRoutines() + routine
        val rawList = gson.toJson(newList)
        dataStore.put(key, rawList)
    }

    override suspend fun deleteRoutine() = dataStore.delete(key)

    override suspend fun clear() = dataStore.clear()
}