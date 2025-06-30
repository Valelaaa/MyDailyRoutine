package md.keeproblems.mydailyroutine.ui.createRoutineScreen.components

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import md.keeproblems.mydailyroutine.ui.theme.components.RoutineText

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun CreateRoutineScreenTopBar(
    onBackClick: () -> Unit,
    onCreateButtonClick: () -> Unit
) {
    TopAppBar(
        title = {
            RoutineText("Create Routine")
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = "navigate_back"
                )
            }
        },
        actions = {
            IconButton(
                onClick = onCreateButtonClick
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
private fun CreateRoutineScreenTopBarPreview() {
    CreateRoutineScreenTopBar(
        onBackClick = {},
        onCreateButtonClick = {}
    )
}