package md.keeproblems.mydailyroutine.ui.createRoutineScreen.components

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import md.keeproblems.mydailyroutine.ui.theme.components.RoutineText

@Composable
internal fun TextFieldValueWithLabel(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            RoutineText(label)
        }
    )
}

@Preview
@Composable
internal fun TextFieldValueWithLabelPreview() {
    TextFieldValueWithLabel(
        value = TextFieldValue("data value"),
        onValueChange = {},
        label = "Data",
    )
}