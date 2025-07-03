package md.keeproblems.mydailyroutine.ui.navigation

sealed class MyRoutineRoutes(val route: String) {
    data object HomePage : MyRoutineRoutes("home_page")
    data object CreateRoutineScreen : MyRoutineRoutes("create_calendar")
    data class RoutineDetailsScreen(val routineId: String) :
        MyRoutineRoutes("routine_details_screen/$routineId") {
        companion object {
            const val RAW_ROUTE = "routine_details_screen/{routineId}"
            const val ARG_ROUTINE_ID = "routineId"
        }
    }

    data object NavigateBack : MyRoutineRoutes("")
}