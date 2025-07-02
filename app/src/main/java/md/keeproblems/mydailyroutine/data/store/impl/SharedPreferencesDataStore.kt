package md.keeproblems.mydailyroutine.data.store.impl

import android.content.Context
import android.content.Context.MODE_PRIVATE
import md.keeproblems.mydailyroutine.data.store.DataStore
import md.keeproblems.mydailyroutine.data.store.DataStoreKey
import md.keeproblems.mydailyroutine.data.store.serializer.JsonSerializer
import javax.inject.Inject
import androidx.core.content.edit

class SharedPreferencesDataStore @Inject constructor(
    private val context: Context,
    private val jsonSerializer: JsonSerializer
) : DataStore {
    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREFERENCES_DEFAULT_KEY,
        MODE_PRIVATE
    )

    override suspend fun <T> get(
        key: DataStoreKey,
        type: Class<T>
    ): T? {
        val rawValue = sharedPreferences.getString(key.value, null)
        return jsonSerializer.deserialize(rawValue ?: "", type)
    }

    override suspend fun <T> put(
        key: DataStoreKey,
        value: T
    ) {
        val rawValue = jsonSerializer.serialize(value)
        sharedPreferences.edit{
            putString(key.value, rawValue)
            apply()
        }
    }

    override suspend fun delete(key: DataStoreKey) {
        sharedPreferences.edit{
            putString(key.value, null)
            apply()
        }
    }

    override suspend fun clear() {
        sharedPreferences.edit { clear() }
    }

    override suspend fun <T> getAll(type: Class<T>): List<T> {
        val allEntries = sharedPreferences.all
        val resultList = mutableListOf<T>()

        allEntries.forEach { (_, value) ->
            if (value is String) {
                val obj = jsonSerializer.deserialize<T>(value, type)
                if (obj != null) {
                    resultList.add(obj)
                }
            }
        }

        return resultList
    }

    companion object {
        const val SHARED_PREFERENCES_DEFAULT_KEY = "shared_preferences"
    }
}