package md.keeproblems.mydailyroutine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import md.keeproblems.mydailyroutine.ui.navigation.MyDailyRoutineNavHost
import md.keeproblems.mydailyroutine.ui.navigation.MyRoutineRoutes
import md.keeproblems.mydailyroutine.ui.navigation.NavChannel
import md.keeproblems.mydailyroutine.ui.theme.MyDailyRoutineTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationChannel: NavChannel
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        observeNavigation()
        setContent {
            MyDailyRoutineTheme {
                navController = rememberNavController()
                MyDailyRoutineNavHost(navController)
            }
        }
    }

    private fun observeNavigation() {
        lifecycleScope.launch {
            navigationChannel.navChannel.collect {
                when (it) {
                    MyRoutineRoutes.NavigateBack -> {
                        navController.popBackStack()
                    }

                    else -> {
                        navController.navigate(it.route)
                    }
                }
            }
        }
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyDailyRoutineTheme {
        Greeting("Android")
    }
}