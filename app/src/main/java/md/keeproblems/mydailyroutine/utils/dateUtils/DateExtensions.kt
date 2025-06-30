package md.keeproblems.mydailyroutine.utils.dateUtils

import java.util.Calendar
import java.util.Date

fun Date.addDays(days: Int): Date {
    val calendar = Calendar.getInstance().apply {
        time = this@addDays
        add(Calendar.DAY_OF_YEAR, days)
    }
    return calendar.time
}

fun Date.daysUntil(other: Date): Int {
    val diff = other.time - this.time //millis
    return (diff / (1000 * 60 * 60 * 24)).toInt()
}