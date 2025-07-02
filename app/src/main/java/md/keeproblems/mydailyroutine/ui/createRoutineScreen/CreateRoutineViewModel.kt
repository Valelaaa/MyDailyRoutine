package md.keeproblems.mydailyroutine.ui.createRoutineScreen

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import md.keeproblems.mydailyroutine.domain.model.Routine
import md.keeproblems.mydailyroutine.domain.repository.RoutineRepository
import md.keeproblems.mydailyroutine.domain.service.ThemeSelectorChannel
import md.keeproblems.mydailyroutine.ui.bottomsheets.BottomSheetChannel
import md.keeproblems.mydailyroutine.ui.bottomsheets.BottomSheetType
import md.keeproblems.mydailyroutine.ui.navigation.MyRoutineRoutes
import md.keeproblems.mydailyroutine.ui.navigation.NavChannel
import md.keeproblems.mydailyroutine.ui.theme.RoutineThemes
import md.keeproblems.mydailyroutine.utils.dateUtils.addDays
import java.util.Calendar
import java.util.UUID

@HiltViewModel
internal class CreateRoutineViewModel @Inject constructor(
    private val navigationChannel: NavChannel,
    private val bottomSheetChannel: BottomSheetChannel,
    private val routineRepository: RoutineRepository,
    private val themeSelectorFlow: ThemeSelectorChannel
) : ViewModel() {
    init {
        observeThemeSelector()
    }

    private val _state = MutableStateFlow(CreateRoutineUiState.EMPTY)

    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
        CreateRoutineUiState.EMPTY
    )
    private var createRoutineJob: Job? = null
    fun onBackClick() {
        navigationChannel.navigateTo(MyRoutineRoutes.NavigateBack)
    }

    fun onCreateRoutine() {
        createRoutineJob = viewModelScope.launch {
            if (createRoutineJob?.isActive == true) return@launch
            trimAllTextFields()
            if (validateBeforeProceed()) {
                val routine = createRoutine()
                withContext(Dispatchers.IO) {
                    routineRepository.saveRoutine(routine)
                }
                withContext(Dispatchers.Main) {
                    navigationChannel.navigateTo(MyRoutineRoutes.NavigateBack)
                }
            }
        }
    }

    private fun trimAllTextFields() {
        _state.update { oldState ->
            oldState.copy(
                titleTextField = oldState.titleTextField.copy(oldState.titleTextField.text.trim()),
                notesTextField = oldState.notesTextField.copy(oldState.notesTextField.text.trim()),
            )
        }
    }

    private fun createRoutine(): Routine {
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        }.time

        val currentState = _state.value
        val period: Int = runCatching {
            currentState.periodInput.text.toInt()
        }.getOrElse { 100 }

        return Routine(
            title = currentState.titleTextField.text,
            note = currentState.notesTextField.text,
            id = UUID.randomUUID().toString(),
            themes = currentState.selectedTheme ?: RoutineThemes.DEFAULT,
            startDate = today,
            endDate = today.addDays(period)
        )
    }

    private fun validateBeforeProceed(): Boolean {
        var isValid = true
        val isTextFieldBlank = _state.value.titleTextField.text.isBlank()

        isValid = isValid && !isTextFieldBlank

        if (isTextFieldBlank) {
            _state.update {
                it.copy(
                    isTitleError = true
                )
            }
        }

        return isValid
    }

    fun updateTitleText(textFieldValue: TextFieldValue) {
        _state.update {
            it.copy(
                titleTextField = textFieldValue,
                isTitleError = false,
            )
        }
    }

    fun updateNote(textFieldValue: TextFieldValue) {
        _state.update {
            it.copy(
                notesTextField = textFieldValue
            )
        }
    }

    fun openThemeSelectorBottomSheet() {
        bottomSheetChannel.show(
            BottomSheetType.SelectThemeBottomSheet
        )
    }

    fun String.isStringMoreThanMaxNumber(): Boolean = runCatching {
        this.toInt() > MAX_PERIOD_INPUT_NUMBER
    }.getOrElse { false }

    fun onPeriodUpdate(textFieldValue: TextFieldValue) {
        val textValue = textFieldValue.text
        if (textValue.any { !it.isDigit() } || textValue.isStringMoreThanMaxNumber()) return
        _state.update {
            it.copy(periodInput = textFieldValue)
        }
    }

    fun onPeriodSelectorClick() {
        bottomSheetChannel.show(BottomSheetType.PeriodSelectorBottomSheet)
    }

    fun onThemeSelect(theme: RoutineThemes) {
        _state.update { it.copy(selectedTheme = theme) }
        bottomSheetChannel.hide()
    }

    private fun observeThemeSelector() {
        viewModelScope.launch {
            themeSelectorFlow.selectedTheme.collect {
                onThemeSelect(it)
            }
        }
    }

    companion object {
        const val MAX_PERIOD_INPUT_NUMBER = 99_999
        const val STOP_TIMEOUT_MILLIS = 5_000L
    }
}
