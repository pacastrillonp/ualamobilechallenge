package co.pacastrillonp.ualamobilechallenge.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import co.pacastrillonp.ualamobilechallenge.R
import co.pacastrillonp.ualamobilechallenge.ui.catalog.CityItem
import co.pacastrillonp.ualamobilechallenge.ui.catalog.SearchTextField
import co.pacastrillonp.ualamobilechallenge.ui.catalog.TextView
import co.pacastrillonp.ualamobilechallenge.ui.navigation.Routes

@Composable
fun CityListPortraitScreen(navController: NavController, viewModel: CityViewModel) {

    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredCities by viewModel.filteredCities.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        SearchTextField(
            searchQuery = searchQuery,
            onValueChange = { query -> viewModel.updateSearchQuery(query) },
        )
        if (filteredCities.isNotEmpty()) {
            LazyColumn {
                items(filteredCities) { city ->
                    CityItem(
                        city = city,
                        onClick = {
                            navController.navigate(
                                "${Routes.CityMap.name}/${city.name}/${city.latitude}/${city.longitude}"
                            )
                        },
                        onFavoriteClick = {
                            viewModel.toggleFavorite(city)
                        }
                    )
                }
            }
        } else {
            TextView(
                text = stringResource(id = R.string.no_cities_found),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            )
        }
    }
}