package md.keeproblems.mydailyroutine.ui.bottomsheets

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import md.keeproblems.mydailyroutine.domain.service.ThemeSelectorChannel
import md.keeproblems.mydailyroutine.ui.theme.RoutineThemes
import javax.inject.Inject


@HiltViewModel
class SelectThemeBottomSheetViewModel @Inject constructor(
    private val themeSelector: ThemeSelectorChannel
) : ViewModel() {

    fun onThemeSelect(theme: RoutineThemes) {
        themeSelector.selectTheme(
            theme
        )
    }
}
