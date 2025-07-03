package md.keeproblems.mydailyroutine.ui.homepage.state

import md.keeproblems.mydailyroutine.ui.theme.RoutineThemes

data class RoutineUiModel(
    val id: String,
    val theme: RoutineThemes,
    val title: String,
    val description: String = "",
    val currentDayIndex: Int,
    val totalDays: Int,
    val selectedPeriod: String = ""
) {
    val currentProgress: Float = currentDayIndex / totalDays.toFloat()

    companion object {
        val EMPTY = RoutineUiModel(
            id = "",
            theme = RoutineThemes.DEFAULT,
            title = "",
            description = "",
            currentDayIndex = 0,
            totalDays = 1
        )
    }
}
