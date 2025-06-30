package md.keeproblems.mydailyroutine.ui.createRoutineScreen

import androidx.compose.ui.text.input.TextFieldValue

data class CreateRoutineUiState(
    val titleTextField: TextFieldValue = TextFieldValue(),
    val notesTextField: TextFieldValue = TextFieldValue(),
) {
    companion object {
        val EMPTY = CreateRoutineUiState()
    }
}
