package co.pacastrillonp.ualamobilechallenge.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.pacastrillonp.ualamobilechallenge.common.model.CityPresentable

@Composable
fun CityAndMapScreen(
    viewModel: CityViewModel
) {
    val filteredCities = viewModel.cities

    var selectedCity by remember { mutableStateOf<CityPresentable?>(null) }

    Row(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxHeight()
                .padding(8.dp)
        ) {
            items(filteredCities.value) { city ->
                CityItem(
                    city = city,
                    onClick = {
                        selectedCity = city
                    },
                    onFavoriteClick = {
                        viewModel.toggleFavorite(city)
                    }
                )
            }
        }

        Box(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxHeight()
        ) {
            if (selectedCity != null) {
                val latitude = selectedCity?.lat ?: 0.0
                val longitude = selectedCity?.lon ?: 0.0

                CityMapScreen(
                    lat = latitude.toFloat(),
                    lon = longitude.toFloat()
                )
            } else {
                CityMapScreen(
                    lat = 0f,
                    lon = 0f
                )
            }
        }
    }
}
