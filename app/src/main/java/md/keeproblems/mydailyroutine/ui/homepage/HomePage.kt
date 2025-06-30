package md.keeproblems.mydailyroutine.ui.homepage

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import md.keeproblems.mydailyroutine.ui.homepage.components.MyDailyRoutineHomePageContentEmptyState
import md.keeproblems.mydailyroutine.ui.homepage.components.MyDailyRoutineHomePageTopBar
import md.keeproblems.mydailyroutine.ui.homepage.components.MyDailyRoutineItem
import md.keeproblems.mydailyroutine.ui.theme.MyDailyRoutineTheme
import md.keeproblems.mydailyroutine.ui.theme.getRoutineThemeByType
import androidx.compose.runtime.getValue
import md.keeproblems.mydailyroutine.ui.homepage.state.RoutineUiModel

@Composable
fun MyDailyRoutineHomePage(
    viewModel: HomePageViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    MyDailyRoutineHomePage(
        routines = state.routines,
        onCreateNewRoutineClick = viewModel::onCreateRoutineClick
    )
}

@Composable
fun MyDailyRoutineHomePage(
    routines: List<RoutineUiModel>,
    onCreateNewRoutineClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            MyDailyRoutineHomePageTopBar(
                onActionClick = onCreateNewRoutineClick
            )
        },
        bottomBar = {},
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues: PaddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
            ) {
                MyDailyRoutineHomePageContent(
                    tasks = routines,
                )
            }
        }
    }
}

@Composable
private fun MyDailyRoutineHomePageContent(
    tasks: List<RoutineUiModel>, modifier: Modifier = Modifier
) {
    val isTasksEmpty = remember(tasks) {
        tasks.isEmpty()
    }

    if (isTasksEmpty) {
        MyDailyRoutineHomePageContentEmptyState()
    } else {
        HorizontalDivider()
        LazyColumn(modifier = modifier) {
            items(items = tasks) { task ->
                MyDailyRoutineItem(
                    title = task.title,
                    selectedRoutineTheme = getRoutineThemeByType(task.theme),
                    progress = task.currentProgress
                )
                HorizontalDivider()
            }
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MyDailyRoutineHomePagePreview() {
    MyDailyRoutineTheme {
        MyDailyRoutineHomePage(
            emptyList(),
            onCreateNewRoutineClick = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MyDailyRoutineHomePageContentPreview() {
    MyDailyRoutineTheme {
        MyDailyRoutineHomePageContent(emptyList())
    }
}