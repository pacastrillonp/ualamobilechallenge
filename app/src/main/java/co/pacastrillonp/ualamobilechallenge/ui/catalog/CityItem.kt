package co.pacastrillonp.ualamobilechallenge.ui.catalog

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.pacastrillonp.ualamobilechallenge.R
import co.pacastrillonp.ualamobilechallenge.common.presentable.CityPresentable
import co.pacastrillonp.ualamobilechallenge.ui.theme.accentColor
import co.pacastrillonp.ualamobilechallenge.ui.theme.primaryColor

@Composable
fun CityItem(
    city: CityPresentable,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                TextView(
                    text = "${city.name}, ${city.country}",
                    modifier = Modifier.padding(bottom = 4.dp),
                    textColor = primaryColor
                )
                Row {
                    TextView(
                        text = "${stringResource(id = R.string.latitude)} ${city.latitude}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    TextView(
                        text = "${stringResource(id = R.string.longitude)} ${city.longitude}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = if (city.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(id = R.string.icon_description),
                    tint = if (city.isFavorite) accentColor else Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCityItem() {
    val sampleCity = CityPresentable(
        id = 1,
        name = "Medellín",
        country = "Colombia",
        latitude = 0.0,
        longitude = 0.0,
        isFavorite = true
    )

    CityItem(
        city = sampleCity,
        onClick = { },
        onFavoriteClick = { }
    )
}