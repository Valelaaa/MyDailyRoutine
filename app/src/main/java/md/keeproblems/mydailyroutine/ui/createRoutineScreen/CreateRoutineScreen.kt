package md.keeproblems.mydailyroutine.ui.createRoutineScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import md.keeproblems.mydailyroutine.ui.createRoutineScreen.components.CreateRoutineScreenTopBar

@Composable
internal fun CreateRoutineScreen(
    viewModel: CreateRoutineViewModel = hiltViewModel(),
) {
    Scaffold(
        topBar = {
            CreateRoutineScreenTopBar(
                onBackClick = viewModel::onBackClick,
                onCreateButtonClick = viewModel::onCreateRoutine,
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .fillMaxSize()
        ) {
            CreateRoutineScreenContent()
        }
    }
}

@Composable
internal fun CreateRoutineScreenContent() {

}


@Preview
@Composable
internal fun CreateRoutineScreenContentPreview() {
    CreateRoutineScreenContent()
}