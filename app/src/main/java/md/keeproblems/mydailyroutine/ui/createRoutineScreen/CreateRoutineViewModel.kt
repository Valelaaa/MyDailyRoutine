package md.keeproblems.mydailyroutine.ui.createRoutineScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import md.keeproblems.mydailyroutine.ui.navigation.NavChannel

@HiltViewModel
class CreateRoutineViewModel @Inject constructor(navigationChannel: NavChannel) : ViewModel() {
    private val _state = MutableStateFlow(CreateRoutineUiState.EMPTY)
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        CreateRoutineUiState.EMPTY
    )

    fun onBackClick() {

    }

    fun onCreateRoutine() {

    }
}
