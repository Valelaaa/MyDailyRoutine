package md.keeproblems.mydailyroutine.ui.navigation

internal sealed class MyRoutineRoutes(val route: String) {
    data object HomePage : MyRoutineRoutes("home_page")
    data object CreateCalendarScreen : MyRoutineRoutes("create_calendar")
}