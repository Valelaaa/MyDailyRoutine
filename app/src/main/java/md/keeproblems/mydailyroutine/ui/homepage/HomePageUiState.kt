package md.keeproblems.mydailyroutine.ui.homepage

import md.keeproblems.mydailyroutine.ui.homepage.state.RoutineUiModel

data class HomePageUiState(
    val routines: List<RoutineUiModel> = emptyList(),
    val isRefreshing: Boolean = false,
) {
    companion object {
        val EMPTY = HomePageUiState()
    }
}