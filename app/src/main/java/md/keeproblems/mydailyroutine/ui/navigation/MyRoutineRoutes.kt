package md.keeproblems.mydailyroutine.ui.navigation

sealed class MyRoutineRoutes(val route: String) {
    data object HomePage : MyRoutineRoutes("home_page")
    data object CreateRoutineScreen : MyRoutineRoutes("create_calendar")
    data object NavigateBack : MyRoutineRoutes("")
}