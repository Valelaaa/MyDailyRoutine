package md.keeproblems.mydailyroutine.domain.service

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import md.keeproblems.mydailyroutine.ui.theme.RoutineThemes

class ThemeSelectorChannel {
    private val _selectedThemeChannel = Channel<RoutineThemes>(Channel.BUFFERED)
    val selectedTheme: Flow<RoutineThemes> = _selectedThemeChannel.receiveAsFlow()

    fun selectTheme(theme: RoutineThemes) {
        _selectedThemeChannel.trySend(theme)
    }
}