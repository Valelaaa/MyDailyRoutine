package md.keeproblems.mydailyroutine.ui.homepage.state

import md.keeproblems.mydailyroutine.ui.theme.RoutineThemes

data class RoutineUiModel(
    val theme: RoutineThemes,
    val title: String,
    val description: String = "",
    val currentDay: Int,
    val totalDays: Int,
) {
    val currentProgress: Float = currentDay / totalDays.toFloat()
}
