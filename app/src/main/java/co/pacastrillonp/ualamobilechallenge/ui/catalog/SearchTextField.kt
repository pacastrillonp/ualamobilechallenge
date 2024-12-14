package co.pacastrillonp.ualamobilechallenge.ui.catalog

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.pacastrillonp.ualamobilechallenge.R

@Composable
fun SearchTextField(
    onValueChange: (String) -> Unit,
    searchQuery: String,
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = { onValueChange(it) },
        label = { TextView(stringResource(id = R.string.search_hint)) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        singleLine = true
    )
}

@Preview
@Composable
fun PreviewSearchTextField() {
    SearchTextField(
        onValueChange = { },
        searchQuery = "Medellín"
    )
}