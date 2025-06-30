package md.keeproblems.mydailyroutine.domain.model

import md.keeproblems.mydailyroutine.ui.theme.RoutineThemes
import md.keeproblems.mydailyroutine.utils.dateUtils.addDays
import java.util.Date

data class Routine(
    val id: String = "",
    val themes: RoutineThemes,
    val title: String,
    val note: String = "",
    val startDate: Date,
    val endDate: Date = startDate.addDays(100),
) {
    val taskList: List<GoalTask> = emptyList()
}

data class GoalTask(
    val isCompleted: Boolean
)


