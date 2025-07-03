package md.keeproblems.mydailyroutine.domain.service

import md.keeproblems.mydailyroutine.domain.model.Routine
import md.keeproblems.mydailyroutine.ui.homepage.state.RoutineUiModel
import md.keeproblems.mydailyroutine.utils.dateUtils.addDays
import md.keeproblems.mydailyroutine.utils.dateUtils.daysUntil
import java.util.Calendar
import java.util.UUID
import javax.inject.Inject

class RoutineMapper @Inject constructor() {
    fun mapUiToDomain(from: RoutineUiModel): Routine {
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.time

        val period: Int = runCatching {
            from.selectedPeriod.toInt()
        }.getOrElse { 100 }

        return Routine(
            title = from.title,
            note = from.description,
            id = UUID.randomUUID().toString(),
            themes = from.theme,
            startDate = today,
            endDate = today.addDays(period)
        )
    }

    fun mapDomainToUi(from: Routine): RoutineUiModel {
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.time
        val startDate = from.startDate
        val endDate = from.endDate

        val totalDays = startDate.daysUntil(endDate)
        val currentDayIndex = startDate.daysUntil(today)
        return RoutineUiModel(
            theme = from.themes,
            id = from.id,
            title = from.title,
            description = from.note,
            currentDayIndex = currentDayIndex,
            totalDays = totalDays,
        )
    }

    fun mapDomainToData() {

    }

    fun mapDataToDomain() {

    }

}
