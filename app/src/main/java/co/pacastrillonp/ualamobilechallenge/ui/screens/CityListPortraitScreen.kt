package co.pacastrillonp.ualamobilechallenge.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.pacastrillonp.ualamobilechallenge.common.model.CityPresentable
import co.pacastrillonp.ualamobilechallenge.ui.navigation.Routes

@Composable
fun CityListScreen(navController: NavController, viewModel: CityViewModel) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredCities = viewModel.getFilteredCities()

    if (filteredCities.isNotEmpty()) {
        LazyColumn {
            items(filteredCities) { city ->
                CityItem(
                    city = city,
                    onClick = {
                        navController.navigate(
                            "${Routes.CityMap.name}/${city.name}/${city.lat}/${city.lon}"
                        )
                    },
                    onFavoriteClick = {
                        viewModel.toggleFavorite(city)
                    }
                )
            }
        }
    } else {
        Text(
            text = "No se encontraron ciudades",
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )
    }
}



@Composable
fun CityItem(city: CityPresentable, onClick: () -> Unit, onFavoriteClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Column(Modifier.weight(1f)) {
            Text(text = "${city.name}, ${city.country}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Lat: ${city.lat}, Lon: ${city.lon}", style = MaterialTheme.typography.bodySmall)
        }
        IconButton(onClick = onFavoriteClick) {
            Icon(
                if (city.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Favorite"
            )
        }
    }
}
