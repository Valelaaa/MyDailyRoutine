package md.keeproblems.mydailyroutine.ui.routinedetails

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import md.keeproblems.mydailyroutine.ui.theme.MyDailyRoutineTheme
import md.keeproblems.mydailyroutine.ui.theme.RoutineTheme
import md.keeproblems.mydailyroutine.ui.theme.components.RoutineText
import md.keeproblems.mydailyroutine.ui.theme.getRoutineThemeByType
import md.keeproblems.mydailyroutine.ui.theme.spacing
import kotlin.random.Random


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
            GitCalendar(theme = getRoutineThemeByType(uiState.selectedRoutine.theme))
        }
    }
}

@Composable
fun GitCalendar(theme: RoutineTheme) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        RoutineText(
            text = "Activities",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp
        )
        GitCalendarContainer(theme)
    }
}

@Composable
fun GitCalendarContainer(theme: RoutineTheme) {
    var topWeekHeader by remember {
        mutableStateOf(IntSize.Zero)
    }
    val daysOfWeek = listOf("Mon", "Wed", "Fri")
    Column(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = MaterialTheme.shapes.medium
            )
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier.padding(
                    top =
                        with(LocalDensity.current) {
                            topWeekHeader.height.toDp()
                        }
                ),
            ) {
                daysOfWeek.forEachIndexed { index, day ->
                    RoutineText(
                        text = day,
                        modifier = Modifier.offset(
                            y = (with(LocalDensity.current) {
                                topWeekHeader.height.toDp() * 2
                            }) * (index + 0.5f)
                        ),
                        textAlign = TextAlign.End
                    )
                }
            }
            CalendarRow(
                theme,
                monthHeightProvider = {
                    topWeekHeader = it
                })
        }
    }
}

@Composable
fun CalendarRow(
    theme: RoutineTheme,
    monthHeightProvider: (IntSize) -> Unit
) {
    val months = listOf(
        "Jan", "Feb", "Mar", "Apr", "May", "Jun",
        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    )
    val monthIterator = months.iterator()
    val weeks: List<Pair<Int, String>> = (0..47)
        .toList()
        .map { it to if (it % 4 == 0) monthIterator.next() else "" }
    val weekLists = weeks.map { remember { List(7) { 0.2f + Random.nextInt(0, 8) * 0.1f } } }
    var textSize by remember {
        mutableStateOf(IntSize.Zero)
    }

    LaunchedEffect(textSize) {
        monthHeightProvider(textSize)
    }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.extraSmall)
    ) {
        itemsIndexed(weeks) { index, week ->
            Column(Modifier.padding(end = if (index == weeks.size - 1) 12.dp else 0.dp)) {
                OverlayAnchor(offset = DpOffset(0.dp, 0.dp)) {
                    RoutineText(
                        text = week.second,
                        modifier = Modifier
                            .onGloballyPositioned { componentSize ->
                                textSize = componentSize.size
                            },
                        maxLines = 1
                    )
                }
                WeekColumn(
                    weekLists[index],
                    theme = theme,
                    modifier = Modifier.padding(top = with(LocalDensity.current) {
                        textSize.height.toDp()
                    })
                )
            }
        }
    }

}

@Composable
fun OverlayAnchor(
    offset: DpOffset = DpOffset(0.dp, 0.dp),
    content: @Composable BoxScope.() -> Unit
) {
    Layout(
        content = {
            Box(
                modifier = Modifier
                    .offset(offset.x, offset.y),
                content = content
            )
        },
        measurePolicy = { measurables, constraints ->
            val placeables = measurables.map { it.measure(Constraints()) }
            layout(0, 0) {
                placeables.forEach {
                    it.place(0, 0)
                }
            }
        }
    )
}

@Composable
fun WeekColumn(
    weekDays: List<Float>,
    theme: RoutineTheme,
    modifier: Modifier = Modifier,
) {
    if (weekDays.size > 7) return
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
    ) {
        weekDays.forEachIndexed { index, it ->
            WeekDay(theme.primaryBackgroundColor.copy(it))
        }
    }
}

@Composable
fun WeekDay(dayColor: Color) {
    Box(
        modifier = Modifier
            .background(
                color = dayColor,
                shape = MaterialTheme.shapes.extraSmall
            )
            .size(20.dp)
    ) {

    }
}


@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun RoutineDetailsScreenPreview() {
    MyDailyRoutineTheme {
        RoutineDetailsScreen(uiState = RoutineDetailsUiState.EMPTY)
    }
}