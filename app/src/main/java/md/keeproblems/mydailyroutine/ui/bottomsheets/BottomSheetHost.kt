package md.keeproblems.mydailyroutine.ui.bottomsheets

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import md.keeproblems.mydailyroutine.ui.bottomsheets.bottomSheetSelectors.SelectThemeBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BottomSheetHost(
    bottomSheetChannel: BottomSheetChannel,
) {
    val bottomSheetState by bottomSheetChannel.flow.collectAsState(HiddenBottomSheet)
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    when (bottomSheetState) {
        BottomSheetType.SelectThemeBottomSheet -> {
            SelectThemeBottomSheet(
                sheetState = sheetState,
                onDismiss = { bottomSheetChannel.hide() },
            )
        }

        else -> {

        }
    }
}


