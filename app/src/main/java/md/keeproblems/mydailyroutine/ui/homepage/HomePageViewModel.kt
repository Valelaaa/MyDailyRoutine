package md.keeproblems.mydailyroutine.ui.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import md.keeproblems.mydailyroutine.domain.model.Routine
import md.keeproblems.mydailyroutine.domain.repository.RoutineRepository
import md.keeproblems.mydailyroutine.ui.homepage.state.RoutineUiModel
import md.keeproblems.mydailyroutine.ui.navigation.MyRoutineRoutes
import md.keeproblems.mydailyroutine.ui.navigation.NavChannel
import md.keeproblems.mydailyroutine.utils.dateUtils.daysUntil
import java.util.Calendar

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val navChannel: NavChannel,
    private val routineRepository: RoutineRepository
) : ViewModel() {
    private val _state = MutableStateFlow<HomePageUiState>(HomePageUiState.EMPTY)
    val state = _state.onStart {
        fetchUiData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(0),
        initialValue = HomePageUiState.EMPTY,
    )

    fun onCreateRoutineClick() {
        navChannel.navigateTo(
            MyRoutineRoutes.CreateRoutineScreen
        )
    }

    fun onPullToRefreshAction() {
        viewModelScope.launch {
            _state.update { it.copy(isRefreshing = true) }
            fetchUiData()
            _state.update { it.copy(isRefreshing = false) }
        }
    }

    private fun fetchUiData() {
        viewModelScope.launch {
            val routines = routineRepository
                .getRoutines()
                .getOrElse { emptyList() }
                .map { it.mapRoutine() }

            _state.update {
                it.copy(
                    routines = routines
                )
            }
        }
    }

    private fun Routine.mapRoutine(): RoutineUiModel {
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.time

        val totalDays = startDate.daysUntil(endDate)
        val currentDayIndex = startDate.daysUntil(today)
        return RoutineUiModel(
            theme = this.themes,
            id = this.id,
            title = this.title,
            description = this.note,
            currentDayIndex = currentDayIndex,
            totalDays = totalDays,
        )
    }

}
