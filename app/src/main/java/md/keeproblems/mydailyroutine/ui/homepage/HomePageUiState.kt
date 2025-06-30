package md.keeproblems.mydailyroutine.ui.homepage

import md.keeproblems.mydailyroutine.ui.homepage.state.RoutineUiModel

data class HomePageUiState(
    var routines: List<RoutineUiModel> = emptyList()
) {
    companion object {
        val EMPTY = HomePageUiState()
    }
}