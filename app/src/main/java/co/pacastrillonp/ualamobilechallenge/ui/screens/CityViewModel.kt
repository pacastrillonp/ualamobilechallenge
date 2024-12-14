package co.pacastrillonp.ualamobilechallenge.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.pacastrillonp.ualamobilechallenge.common.model.CityPresentable
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor() : ViewModel() {

    private val _cities = MutableStateFlow(
        listOf(
            CityPresentable("Alabama", "US", 32.806671, -86.79113),
            CityPresentable("Albuquerque", "US", 35.0844, -106.6504),
            CityPresentable("Sydney", "AU", -33.8688, 151.2093)
        )
    )

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    val filteredCities: StateFlow<List<CityPresentable>> =
        combine(_cities, _searchQuery) { cities, query ->
            cities.filter { it.name.contains(query, ignoreCase = true) }
                .sortedBy { it.name }
        }.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun toggleFavorite(city: CityPresentable) {
        _cities.value = _cities.value.map {
            if (it == city) it.copy(isFavorite = !it.isFavorite) else it
        }
    }
}
