package md.keeproblems.mydailyroutine.ui.bottomsheets

import kotlinx.coroutines.flow.Flow

interface BottomSheetChannel {
    val flow: Flow<BottomSheetType>

    fun show(type: BottomSheetType)

    fun hide()
}
