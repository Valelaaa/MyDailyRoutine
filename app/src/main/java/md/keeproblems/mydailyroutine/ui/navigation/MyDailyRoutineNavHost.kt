package md.keeproblems.mydailyroutine.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import md.keeproblems.mydailyroutine.ui.createRoutineScreen.CreateRoutineScreen
import md.keeproblems.mydailyroutine.ui.homepage.MyDailyRoutineHomePage

@Composable
internal fun MyDailyRoutineNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MyRoutineRoutes.HomePage.route) {
        composable(route = MyRoutineRoutes.HomePage.route) {
            MyDailyRoutineHomePage()
        }

        composable(route = MyRoutineRoutes.CreateRoutineScreen.route) {
            CreateRoutineScreen()
        }
    }
}

