package md.keeproblems.mydailyroutine.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import md.keeproblems.mydailyroutine.ui.createroutinescreen.CreateRoutineScreen
import md.keeproblems.mydailyroutine.ui.homepage.MyDailyRoutineHomePage
import md.keeproblems.mydailyroutine.ui.routinedetails.RoutineDetailsScreen

@Composable
internal fun MyDailyRoutineNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = MyRoutineRoutes.HomePage.route
    ) {
        composable(route = MyRoutineRoutes.HomePage.route) {
            MyDailyRoutineHomePage()
        }

        composable(route = MyRoutineRoutes.CreateRoutineScreen.route) {
            CreateRoutineScreen()
        }

        composable(
            route = MyRoutineRoutes.RoutineDetailsScreen.RAW_ROUTE,
            arguments = listOf(
                navArgument(MyRoutineRoutes.RoutineDetailsScreen.ARG_ROUTINE_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val routineId = backStackEntry.arguments
                ?.getString(MyRoutineRoutes.RoutineDetailsScreen.ARG_ROUTINE_ID)

            if (routineId != null) {
                RoutineDetailsScreen(routineId = routineId)
            }
        }
    }
}



