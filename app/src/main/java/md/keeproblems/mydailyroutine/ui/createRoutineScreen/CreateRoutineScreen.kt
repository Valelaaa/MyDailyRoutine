package md.keeproblems.mydailyroutine.ui.createRoutineScreen

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import md.keeproblems.mydailyroutine.ui.createRoutineScreen.components.CreateRoutineScreenTopBar
import md.keeproblems.mydailyroutine.ui.createRoutineScreen.components.SelectableCard
import md.keeproblems.mydailyroutine.ui.createRoutineScreen.components.TextFieldValueWithLabel
import md.keeproblems.mydailyroutine.ui.theme.RoutineThemes
import md.keeproblems.mydailyroutine.ui.theme.getRoutineThemeByType

@Composable
internal fun CreateRoutineScreen(
    viewModel: CreateRoutineViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    Scaffold(
        topBar = {
            CreateRoutineScreenTopBar(
                onBackClick = viewModel::onBackClick,
                onCreateButtonClick = viewModel::onCreateRoutine,
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(24.dp)
                .fillMaxSize()
        ) {
            CreateRoutineScreenContent(
                titleTextField = state.titleTextField,
                noteTextField = state.notesTextField,
                updateTitle = viewModel::updateTitleText,
                updateNote = viewModel::updateNote,
                openThemeSelectorBottomSheet = viewModel::openThemeSelectorBottomSheet,
                selectedTheme = state.selectedTheme,
                isTitleError = state.isTitleError
            )
        }
    }
}

@Composable
internal fun CreateRoutineScreenContent(
    titleTextField: TextFieldValue,
    noteTextField: TextFieldValue,
    updateTitle: (TextFieldValue) -> Unit,
    updateNote: (TextFieldValue) -> Unit,
    selectedTheme: RoutineThemes?,
    openThemeSelectorBottomSheet: () -> Unit,
    isTitleError: Boolean = false,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextFieldValueWithLabel(
            value = titleTextField,
            onValueChange = updateTitle,
            label = "title",
            placeHolderText = "Give Routine a title",
            modifier = Modifier.fillMaxWidth(),
            isError = isTitleError,
            imeAction = ImeAction.Next,
            errorMessage = "This is Required Field"
        )
        TextFieldValueWithLabel(
            value = noteTextField,
            onValueChange = updateNote,
            label = "description",
            placeHolderText = "Give it description",
            modifier = Modifier.fillMaxWidth(),
            imeAction = ImeAction.Done,
        )

        SelectableCard(
            value = selectedTheme?.name,
            onClick = openThemeSelectorBottomSheet,
            placeHolder = "Select routine theme",
            label = "Routine Theme:",
            modifier = Modifier
                .fillMaxWidth(),
            trailingIcon = {
                Column(
                    Modifier
                        .background(
                            color = getRoutineThemeByType(selectedTheme).primaryColor,
                            shape = MaterialTheme.shapes.small,
                        )
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = MaterialTheme.shapes.small
                        )
                        .size(36.dp)
                ) {}
            }
        )
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun CreateRoutineScreenContentPreview() {
    CreateRoutineScreenContent(
        titleTextField = TextFieldValue(),
        noteTextField = TextFieldValue(),
        updateTitle = {},
        updateNote = {},
        selectedTheme = null,
        openThemeSelectorBottomSheet = {}
    )
}
