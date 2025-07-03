package md.keeproblems.mydailyroutine.ui.routinedetails

import md.keeproblems.mydailyroutine.ui.homepage.state.RoutineUiModel

internal data class RoutineDetailsUiState(
    val isLoading: Boolean = false,
    val selectedRoutine: RoutineUiModel = RoutineUiModel.EMPTY
) {
    companion object {
        val EMPTY = RoutineDetailsUiState()
    }
}
