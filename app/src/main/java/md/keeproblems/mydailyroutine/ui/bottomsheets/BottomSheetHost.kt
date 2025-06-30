package md.keeproblems.mydailyroutine.ui.bottomsheets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.Flow

@Composable
fun BottomSheetHost(bottomSheetFlow: Flow<BottomSheetType>) {
    val bottomSheetState by bottomSheetFlow.collectAsState(BottomSheetType.Hidden)
    when (bottomSheetState) {
        BottomSheetType.SelectThemeBottomSheet -> {

        }

        else -> {

        }
    }
}