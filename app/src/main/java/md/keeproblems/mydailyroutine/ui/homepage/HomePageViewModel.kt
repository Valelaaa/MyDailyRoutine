package md.keeproblems.mydailyroutine.ui.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import md.keeproblems.mydailyroutine.ui.homepage.state.RoutineUiModel
import md.keeproblems.mydailyroutine.ui.navigation.MyRoutineRoutes
import md.keeproblems.mydailyroutine.ui.navigation.NavChannel
import md.keeproblems.mydailyroutine.ui.theme.RoutineThemes
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(private val navChannel: NavChannel) : ViewModel() {
    private val _state = MutableStateFlow<HomePageUiState>(HomePageUiState.EMPTY)
    val state = _state.onStart {
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = HomePageUiState.EMPTY,
    )

    fun onCreateRoutineClick() {
        navChannel.navigateTo(
            MyRoutineRoutes.CreateRoutineScreen
        )
        _state.update {
            it.copy(
                routines = listOf(
                    RoutineUiModel(
                        theme = RoutineThemes.DEFAULT,
                        title = "Routine",
                        description = "",
                        currentDay = 1,
                        totalDays = 10
                    )
                )
            )
        }
    }
}
