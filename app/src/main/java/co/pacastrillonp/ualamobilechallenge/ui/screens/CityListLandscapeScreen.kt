package co.pacastrillonp.ualamobilechallenge.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.pacastrillonp.ualamobilechallenge.R
import co.pacastrillonp.ualamobilechallenge.common.presentable.CityPresentable
import co.pacastrillonp.ualamobilechallenge.common.util.Constants.Routes.DEFAULT_ORIENTATION
import co.pacastrillonp.ualamobilechallenge.ui.catalog.CityItem
import co.pacastrillonp.ualamobilechallenge.ui.catalog.SearchTextField
import co.pacastrillonp.ualamobilechallenge.ui.catalog.TextView

@Composable
fun CityListLandscapeScreen(viewModel: CityViewModel) {

    val searchQuery by viewModel.searchQuery.collectAsState()
    val filteredCities by viewModel.cities.collectAsState()
    val listState = rememberLazyListState()
    var selectedCity by remember { mutableStateOf<CityPresentable?>(null) }

    val lastVisibleIndex by remember {
        derivedStateOf { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
    }

    LaunchedEffect(lastVisibleIndex) {
        if (lastVisibleIndex == filteredCities.size - 1) {
            viewModel.loadNextPage()
        }
    }

    Row(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .weight(0.4f)
                .fillMaxHeight()
        ) {
            SearchTextField(
                searchQuery = searchQuery,
                onValueChange = { query -> viewModel.updateSearchQuery(query) },
            )

            if (filteredCities.isNotEmpty()) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.padding(8.dp)
                ) {
                    items(filteredCities) { city ->
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
            } else {
                TextView(
                    text = stringResource(id = R.string.no_cities_found),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .weight(0.6f)
                .fillMaxHeight()
        ) {
            val latitude = selectedCity?.latitude ?: DEFAULT_ORIENTATION
            val longitude = selectedCity?.longitude ?: DEFAULT_ORIENTATION
            CityMapScreen(
                latitude = latitude.toFloat(),
                longitude = longitude.toFloat()
            )
        }
    }
}
