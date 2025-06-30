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
        println("!!! + log getAllRoutines: start reading from dataStore")
        val rawValue = dataStore.get(key, String::class.java)
        println("!!! + log getAllRoutines: rawValue = $rawValue")
        if (rawValue.isNullOrEmpty()) {
            println("!!! + log getAllRoutines: rawValue is null or empty, return emptyList")
            return emptyList()
        }
        val type = object : TypeToken<List<Routine>>() {}.type
        val list: List<Routine> = gson.fromJson(rawValue, type)
        println("!!! + log getAllRoutines: parsed list size = ${list.size}")
        return list
    }

    override suspend fun getRoutine(routineId: String): Routine? {
        val currentList = getAllRoutines()
        return currentList.find { it.id == routineId }
    }

    override suspend fun putRoutine(routine: Routine) {
        println("!!! + log putRoutine: start saving routine with id = ${routine.id}")
        val newList = getAllRoutines() + routine
        println("!!! + log putRoutine: new list size = ${newList.size}")
        val rawList = gson.toJson(newList)
        println("!!! + log putRoutine: json to save = $rawList")
        dataStore.put(key, rawList)
        println("!!! + log putRoutine: saved successfully")
    }

    override suspend fun deleteRoutine() = dataStore.delete(key)

    override suspend fun clear() = dataStore.clear()
}