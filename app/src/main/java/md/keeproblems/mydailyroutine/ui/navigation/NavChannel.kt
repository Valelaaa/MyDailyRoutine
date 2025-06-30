package md.keeproblems.mydailyroutine.ui.navigation

import kotlinx.coroutines.flow.Flow

interface NavChannel {
    val navChannel: Flow<MyRoutineRoutes>

    fun navigateTo(destination: MyRoutineRoutes)
}
