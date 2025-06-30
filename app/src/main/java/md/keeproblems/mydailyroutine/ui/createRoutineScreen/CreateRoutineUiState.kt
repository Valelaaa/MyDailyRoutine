package md.keeproblems.mydailyroutine.ui.createRoutineScreen

import androidx.compose.ui.text.input.TextFieldValue
import md.keeproblems.mydailyroutine.ui.theme.RoutineThemes

data class CreateRoutineUiState(
    val titleTextField: TextFieldValue = TextFieldValue(),
    val notesTextField: TextFieldValue = TextFieldValue(),
    val selectedTheme: RoutineThemes? = null,
    val isTitleError: Boolean = false,
) {
    companion object {
        val EMPTY = CreateRoutineUiState()
    }
}
