package md.keeproblems.mydailyroutine.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
internal fun MyDailyRoutineNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MyRoutineRoutes.HomePage) {
        composable(route = MyRoutineRoutes.HomePage.route) {

        }

        composable(route = MyRoutineRoutes.HomePage.route) {

        }
    }
}