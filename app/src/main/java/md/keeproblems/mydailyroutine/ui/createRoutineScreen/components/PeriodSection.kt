package md.keeproblems.mydailyroutine.ui.createRoutineScreen.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import md.keeproblems.mydailyroutine.ui.theme.MyDailyRoutineTheme
import md.keeproblems.mydailyroutine.ui.theme.components.RoutineText

@Composable
internal fun PeriodSection(
    periodInput: TextFieldValue,
    onPeriodUpdate: (TextFieldValue) -> Unit,
    onPeriodSelectorClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PeriodSelector(
            onPeriodSelectorClick = onPeriodSelectorClick,
            modifier = Modifier.padding(top = 6.dp)
        )
        PeriodTextField(
            value = periodInput,
            onValueChange = onPeriodUpdate,
            label = "Up to 99999",
        )
    }
}

@Composable
internal fun PeriodSelector(
    onPeriodSelectorClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = false
) {
    val selectorShape = MaterialTheme.shapes.extraSmall

    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = selectorShape,
            )
            .clip(selectorShape)
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = selectorShape
            )
            .clickable(enabled = enabled) {
                onPeriodSelectorClick()
            }
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RoutineText(
            "Days:",
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.SemiBold,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
        )
        if(enabled) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "select",
                tint = MaterialTheme.colorScheme.surfaceTint,
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}

@Composable
internal fun PeriodTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    label: String = "",
    placeHolder: String = "",
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        keyboardActions = KeyboardActions {

        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number,
        ),
        label = if (label.isNotBlank()) {
            {
                RoutineText(
                    text = label
                )
            }
        } else null,
        placeholder = if (placeHolder.isNotBlank()) {
            {
                RoutineText(text = placeHolder)
            }
        } else null
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PeriodSectionPreview() {
    MyDailyRoutineTheme {
        PeriodSection(
            periodInput = TextFieldValue(),
            onPeriodUpdate = {},
            onPeriodSelectorClick = {}
        )
    }
}