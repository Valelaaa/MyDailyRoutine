package md.keeproblems.mydailyroutine.data.store

sealed class DataStoreKey(val value: String) {
    data object RoutineStoreKey : DataStoreKey(value = "routine_store_key")
}