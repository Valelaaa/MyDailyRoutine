package md.keeproblems.mydailyroutine.data.store.impl

import jakarta.inject.Inject
import md.keeproblems.mydailyroutine.data.store.DataStore
import md.keeproblems.mydailyroutine.data.store.DataStoreKey

class LiveSessionDataStore @Inject constructor() : DataStore {
    private val data = mutableMapOf<DataStoreKey, Any?>()

    override suspend fun <T> get(key: DataStoreKey, type: Class<T>): T? {
        return data[key]?.let { value ->
            if (type.isInstance(value)) {
                @Suppress("UNCHECKED_CAST")
                value as T
            } else null
        }
    }

    override suspend fun <T> put(key: DataStoreKey, value: T) {
        data[key] = value
    }

    override suspend fun delete(key: DataStoreKey) {
        data.remove(key)
    }

    override suspend fun clear() {
        data.clear()
    }

    override suspend fun <T> getAll(type: Class<T>): List<T> {
        return data.values.mapNotNull { value ->
            if (type.isInstance(value)) {
                @Suppress("UNCHECKED_CAST")
                value as T
            } else null
        }
    }
}
