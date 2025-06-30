package md.keeproblems.mydailyroutine.ui.bottomsheets

sealed interface BottomSheetType {
    data object Hidden : BottomSheetType
    data object SelectThemeBottomSheet : BottomSheetType
}