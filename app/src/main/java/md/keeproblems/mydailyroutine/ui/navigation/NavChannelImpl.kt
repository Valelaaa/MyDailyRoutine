package md.keeproblems.mydailyroutine.ui.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class NavChannelImpl : NavChannel {
    private val _navChannel = Channel<MyRoutineRoutes>(Channel.BUFFERED)
    override val navChannel: Flow<MyRoutineRoutes> = _navChannel.receiveAsFlow()

    override fun navigateTo(destination: MyRoutineRoutes) {
        _navChannel.trySend(destination)
    }
}