package md.keeproblems.mydailyroutine.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class RoutineTheme(
    val primaryColor: Color,
    val secondaryColor: Color,
    val primaryBackgroundColor: Color,
    val secondaryBackgroundColor: Color,
) {
    companion object {
        val defaultRoutineTheme: RoutineTheme
            @Composable
            get() = RoutineTheme(
                primaryColor = MaterialTheme.colorScheme.primary,
                primaryBackgroundColor = MaterialTheme.colorScheme.primaryContainer,
                secondaryColor = Color.Unspecified,
                secondaryBackgroundColor = Color.Unspecified
            )
    }
}

enum class RoutineThemes {
    DEFAULT,
}

@Composable
fun getRoutineThemeByType(routineThemes: RoutineThemes): RoutineTheme {
    return when (routineThemes) {
        RoutineThemes.DEFAULT -> RoutineTheme.defaultRoutineTheme
    }
}

@Composable
fun MaterialTheme.defaultRoutineTheme(): RoutineTheme = RoutineTheme.defaultRoutineTheme