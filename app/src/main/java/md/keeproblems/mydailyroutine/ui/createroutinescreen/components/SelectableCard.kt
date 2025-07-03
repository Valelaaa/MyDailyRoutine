package md.keeproblems.mydailyroutine.ui.createroutinescreen.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import md.keeproblems.mydailyroutine.ui.theme.MyDailyRoutineTheme
import md.keeproblems.mydailyroutine.ui.theme.components.RoutineText

@Composable
fun SelectableCard(
    value: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    placeHolder: String = "",
    label: String = "",
    enabled: Boolean = true,
    trailingIcon: @Composable () -> Unit = {},
) {
    val isValueStateValid = remember(value) {
        value != null && value.isNotBlank()
    }

    Card(
        onClick = onClick,
        enabled = enabled
    ) {
        Row(
            modifier = modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                if (isValueStateValid && label.isNotBlank()) {
                    RoutineText(
                        label, fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
                Row {
                    if (isValueStateValid) {
                        RoutineText(text = value ?: "")
                    } else {
                        RoutineText(
                            text = placeHolder,
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            }
            trailingIcon()
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SelectableCardPreview() {
    MyDailyRoutineTheme {
        SelectableCard(
            value = "value",
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
            label = "label"
        )
    }
}
