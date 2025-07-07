package md.keeproblems.mydailyroutine.ui.homepage

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
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
        onCreateNewRoutineClick = viewModel::onCreateRoutineClick,
        onPullToRefreshAction = viewModel::onPullToRefreshAction,
        onRoutineClick = viewModel::onRoutineClick,
        isRefreshing = state.isRefreshing
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDailyRoutineHomePage(
    isRefreshing: Boolean = false,
    onPullToRefreshAction: () -> Unit,
    routines: List<RoutineUiModel>,
    onCreateNewRoutineClick: () -> Unit,
    onRoutineClick: (String) -> Unit,
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
            PullToRefreshBox(
                isRefreshing = isRefreshing,
                onRefresh = onPullToRefreshAction,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp),
                ) {
                    MyDailyRoutineHomePageContent(
                        tasks = routines,
                        onRoutineClick = onRoutineClick
                    )
                }
            }
        }
    }
}

@Composable
private fun MyDailyRoutineHomePageContent(
    tasks: List<RoutineUiModel>,
    onRoutineClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val isTasksEmpty = remember(tasks) {
        tasks.isEmpty()
    }

    if (isTasksEmpty) {
        MyDailyRoutineHomePageContentEmptyState(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        )
    } else {
        HorizontalDivider()
        LazyColumn(modifier = modifier) {
            items(items = tasks) { task ->
                MyDailyRoutineItem(
                    routine = task,
                    selectedRoutineTheme = getRoutineThemeByType(task.theme),
                    progress = task.currentProgress,
                    onClick = onRoutineClick
                )
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
            routines = emptyList(),
            onRoutineClick = {},
            onPullToRefreshAction = {},
            isRefreshing = true,
            onCreateNewRoutineClick = {}
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MyDailyRoutineHomePageContentPreview() {
    MyDailyRoutineTheme {
        MyDailyRoutineHomePageContent(
            tasks = emptyList(),
            onRoutineClick = {})
    }
}