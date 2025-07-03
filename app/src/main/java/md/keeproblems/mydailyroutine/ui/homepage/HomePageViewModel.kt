package md.keeproblems.mydailyroutine.ui.homepage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import md.keeproblems.mydailyroutine.domain.repository.RoutineRepository
import md.keeproblems.mydailyroutine.domain.service.RoutineMapper
import md.keeproblems.mydailyroutine.ui.navigation.MyRoutineRoutes
import md.keeproblems.mydailyroutine.ui.navigation.NavChannel

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val navChannel: NavChannel,
    private val routineRepository: RoutineRepository,
    private val routineMapper: RoutineMapper
) : ViewModel() {
    private val _state = MutableStateFlow<HomePageUiState>(HomePageUiState.EMPTY)
    val state = _state.onStart {
        fetchUiData()
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(0),
        initialValue = HomePageUiState.EMPTY,
    )

    var navigationJob: Job? = null

    fun onCreateRoutineClick() {
        navigateTo(MyRoutineRoutes.CreateRoutineScreen)
    }

    fun onPullToRefreshAction() {
        viewModelScope.launch {
            _state.update { it.copy(isRefreshing = true) }
            fetchUiData()
            _state.update { it.copy(isRefreshing = false) }
        }
    }

    fun onRoutineClick(routineId: String) {
        if (routineId.isEmpty()) return

        navigateTo(MyRoutineRoutes.RoutineDetailsScreen(routineId))
    }

    private fun fetchUiData(getCached: Boolean = false) {
        viewModelScope.launch {
            val routines = routineRepository
                .getRoutines(getCached = getCached)
                .getOrElse { emptyList() }
                .map(routineMapper::mapDomainToUi)

            _state.update {
                it.copy(
                    routines = routines
                )
            }
        }
    }

    private fun navigateTo(destination: MyRoutineRoutes) {
        if (navigationJob?.isActive == true) return

        navigationJob = viewModelScope.launch {
            navChannel.navigateTo(destination)
        }
    }

}
