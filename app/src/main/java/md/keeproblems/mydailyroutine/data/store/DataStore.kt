package md.keeproblems.mydailyroutine.data.store

interface DataStore {

    suspend fun <T> get(key: DataStoreKey, type: Class<T>): T?

    suspend fun <T> put(key: DataStoreKey, value: T)

    suspend fun delete(key: DataStoreKey)

    suspend fun clear()

    suspend fun <T> getAll(type: Class<T>): List<T>
}
