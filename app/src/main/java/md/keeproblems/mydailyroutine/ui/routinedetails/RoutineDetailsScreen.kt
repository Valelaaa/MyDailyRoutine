package md.keeproblems.mydailyroutine.ui.routinedetails

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import md.keeproblems.mydailyroutine.ui.theme.getRoutineThemeByType
import md.keeproblems.mydailyroutine.ui.theme.spacing


@Composable
internal fun RoutineDetailsScreen(
    routineId: String,
    viewModel: RoutineDetailsViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsState()

    LaunchedEffect(uiState.selectedRoutine) {
        viewModel.loadRoutine(routineId)
    }

    RoutineDetailsScreen(
        uiState = uiState,
    )
}

@Composable
internal fun RoutineDetailsScreen(
    uiState: RoutineDetailsUiState,
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(MaterialTheme.spacing.horizontalScreenPaddings)
        ) {
            Column(
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        getRoutineThemeByType(uiState.selectedRoutine.theme)
                            .primaryBackgroundColor,
                        CircleShape
                    ),
            ) {

            }
        }
    }
}


@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun RoutineDetailsScreenPreview() {
    RoutineDetailsScreen(uiState = RoutineDetailsUiState.EMPTY)
}