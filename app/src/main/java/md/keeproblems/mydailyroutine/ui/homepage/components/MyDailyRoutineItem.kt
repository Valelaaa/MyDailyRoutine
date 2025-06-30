package md.keeproblems.mydailyroutine.ui.homepage.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import md.keeproblems.mydailyroutine.ui.theme.RoutineTheme
import md.keeproblems.mydailyroutine.ui.theme.components.RoutineText
import md.keeproblems.mydailyroutine.ui.theme.defaultRoutineTheme

@Composable
internal fun MyDailyRoutineItem(
    title: String,
    modifier: Modifier = Modifier,
    selectedRoutineTheme: RoutineTheme = MaterialTheme.defaultRoutineTheme(),
    progress: Float = 0f,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {}
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator(
            trackColor = selectedRoutineTheme.primaryBackgroundColor,
            color = selectedRoutineTheme.primaryColor,
            modifier = Modifier.size(24.dp),
            strokeWidth = 2.dp,
            progress = { progress }
        )
        RoutineText(
            text = title,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun MyDailyRoutineItemPreview() {
    MaterialTheme {
        MyDailyRoutineItem(title = "Routine")
    }
}