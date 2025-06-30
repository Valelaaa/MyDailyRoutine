package md.keeproblems.mydailyroutine.ui.bottomsheets

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class BottomSheetChannelImpl : BottomSheetChannel {
    private val _bottomSheetChannel = Channel<BottomSheetType>(Channel.BUFFERED)
    override val flow: Flow<BottomSheetType> =
        _bottomSheetChannel.receiveAsFlow()

    override fun show(type: BottomSheetType) {
        _bottomSheetChannel.trySend(type)
    }
}