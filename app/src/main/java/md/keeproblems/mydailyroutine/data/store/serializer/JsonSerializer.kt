package md.keeproblems.mydailyroutine.data.store.serializer

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

class JsonSerializer @Inject constructor(
    val gson: Gson
) {
    inline fun <reified T> serialize(any: T?): String {
        return gson.toJson(any)
    }

    inline fun <reified T> deserialize(rawValue: String): T? {
        val type = object : TypeToken<T>() {}.type
        return gson.fromJson(rawValue, type)
    }

    fun <T> deserialize(rawValue: String, type: Type): T? {
        return gson.fromJson(rawValue, type)
    }
}