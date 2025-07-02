package md.keeproblems.mydailyroutine.ui.homepage.components

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import md.keeproblems.mydailyroutine.ui.theme.MyDailyRoutineTheme
import md.keeproblems.mydailyroutine.ui.theme.components.RoutineText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MyDailyRoutineHomePageTopBar(onActionClick: () -> Unit = {}) {
    CenterAlignedTopAppBar(
        title = {
            RoutineText("Routine App")
        },
        actions = {
            IconButton(
                onClick = onActionClick
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MyDailyRoutineHomePageTopBarPreview() {
    MyDailyRoutineTheme {
        MyDailyRoutineHomePageTopBar(onActionClick = {})
    }
}