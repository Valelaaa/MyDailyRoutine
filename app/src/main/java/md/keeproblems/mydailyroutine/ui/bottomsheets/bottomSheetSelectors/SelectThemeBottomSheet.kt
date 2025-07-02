package md.keeproblems.mydailyroutine.ui.bottomsheets.bottomSheetSelectors

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import md.keeproblems.mydailyroutine.ui.bottomsheets.SelectThemeBottomSHeetViewModel
import md.keeproblems.mydailyroutine.ui.theme.MyDailyRoutineTheme
import md.keeproblems.mydailyroutine.ui.theme.RoutineThemes
import md.keeproblems.mydailyroutine.ui.theme.getRoutineThemeByType

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SelectThemeBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    createRoutineViewModel: SelectThemeBottomSHeetViewModel = hiltViewModel()
) {
    SelectThemeBottomSheet(
        sheetState = sheetState,
        onDismiss = onDismiss,
        onThemeSelect = createRoutineViewModel::onThemeSelect
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SelectThemeBottomSheet(
    sheetState: SheetState,
    onDismiss: () -> Unit,
    onThemeSelect: (RoutineThemes) -> Unit
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss
    ) {
        SelectThemeBottomSheetContent(
            themes = RoutineThemes.entries,
            onSelectThemeClick = onThemeSelect
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectThemeBottomSheetContent(
    themes: List<RoutineThemes>,
    onSelectThemeClick: (RoutineThemes) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .padding(bottom = 40.dp),
    ) {
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            themes.forEach {
                SelectThemeItem(
                    theme = it,
                    modifier = Modifier.size(40.dp),
                    onThemeSelect = onSelectThemeClick
                )
            }
        }
    }
}

@Composable
private fun SelectThemeItem(
    modifier: Modifier = Modifier,
    theme: RoutineThemes,
    onThemeSelect: (RoutineThemes) -> Unit = {}
) {
    val shape = CircleShape
    Column(
        modifier = modifier
            .border(
                width = 1.dp, color = MaterialTheme.colorScheme.outline, shape = shape
            )
            .background(
                color = getRoutineThemeByType(theme).primaryBackgroundColor, shape = shape
            )
            .clip(shape)
            .clickable(enabled = true) {
                onThemeSelect(theme)
            }) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
private fun SelectThemeBottomSheetPreview() {
    MyDailyRoutineTheme {
        val sheetState = rememberStandardBottomSheetState()
        SelectThemeBottomSheet(
            sheetState = sheetState,
            onDismiss = {},
            onThemeSelect = {}
        )
    }
}