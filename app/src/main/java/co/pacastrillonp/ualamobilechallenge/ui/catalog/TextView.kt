package co.pacastrillonp.ualamobilechallenge.ui.catalog

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextView(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color.Unspecified,
    textAlign: TextAlign? = null
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        color = textColor,
        textAlign = textAlign
    )
}

@Preview
@Composable
private fun TitleTextPreview() {
    TextView("Title Text")
}