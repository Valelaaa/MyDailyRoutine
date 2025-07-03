package md.keeproblems.mydailyroutine.ui.routinedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import md.keeproblems.mydailyroutine.domain.repository.RoutineRepository
import md.keeproblems.mydailyroutine.domain.service.RoutineMapper
import md.keeproblems.mydailyroutine.utils.dateUtils.consts.DEFAULT_REPLAY_EXPIRATION_MILLIS
import javax.inject.Inject

@HiltViewModel
internal class RoutineDetailsViewModel @Inject constructor(
    private val routineRepository: RoutineRepository,
    private val routineMapper: RoutineMapper
) : ViewModel() {
    private val _state = MutableStateFlow(RoutineDetailsUiState.EMPTY)
    val state = _state.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(DEFAULT_REPLAY_EXPIRATION_MILLIS),
        RoutineDetailsUiState.EMPTY
    )

    fun loadRoutine(routineId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val routineDomain = routineRepository.getRoutineById(routineId).getOrElse {
                return@launch
            }
            if (routineDomain == null) return@launch
            val mappedRoutine = routineMapper.mapDomainToUi(routineDomain)

            _state.update { it.copy(selectedRoutine = mappedRoutine, isLoading = false) }
        }
    }

}
