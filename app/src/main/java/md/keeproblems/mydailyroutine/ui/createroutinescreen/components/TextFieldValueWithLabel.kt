package md.keeproblems.mydailyroutine.ui.createroutinescreen.components

import android.content.res.Configuration
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import md.keeproblems.mydailyroutine.ui.theme.MyDailyRoutineTheme
import md.keeproblems.mydailyroutine.ui.theme.components.RoutineText

@Composable
internal fun TextFieldValueWithLabel(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String,
    placeHolderText: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    imeAction: ImeAction = ImeAction.None,
    errorMessage: String = "",
) {
    OutlinedTextField(
        onValueChange = onValueChange,
        value = value,
        label = {
            RoutineText(label)
        },
        modifier = modifier,
        placeholder = {
            RoutineText(placeHolderText)
        },
        isError = isError,
        maxLines = 1,
        singleLine = true,
        supportingText = if (isError) {
            {
                RoutineText(errorMessage)
            }
        } else null,
        keyboardActions = KeyboardActions.Default,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction
        )
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
internal fun TextFieldValueWithLabelPreview() {
    MyDailyRoutineTheme {
        TextFieldValueWithLabel(
            value = TextFieldValue("label"),
            onValueChange = {},
            label = "Data",
            placeHolderText = "placeholder",
            isError = true
        )
    }
}