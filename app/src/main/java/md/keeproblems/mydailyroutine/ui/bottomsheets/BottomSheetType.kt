package md.keeproblems.mydailyroutine.ui.bottomsheets

sealed interface BottomSheetType {
    data object PeriodSelectorBottomSheet : BottomSheetType
    data object SelectThemeBottomSheet : BottomSheetType
}