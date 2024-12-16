package co.pacastrillonp.ualamobilechallenge.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.pacastrillonp.ualamobilechallenge.common.presentable.CityPresentable
import co.pacastrillonp.ualamobilechallenge.common.util.Constants.Pagination.PAGE_SIZE
import co.pacastrillonp.ualamobilechallenge.domain.usecases.FetchCitiesUseCase
import co.pacastrillonp.ualamobilechallenge.domain.usecases.LoadCitiesUseCase
import co.pacastrillonp.ualamobilechallenge.domain.usecases.SaveCitiesUseCase
import co.pacastrillonp.ualamobilechallenge.domain.usecases.UpdateFavoriteUseCase
import co.pacastrillonp.ualamobilechallenge.domain.usecases.ValidatePersistedDataUseCase
import co.pacastrillonp.ualamobilechallenge.ui.navigation.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    private val validatePersistedDataUseCase: ValidatePersistedDataUseCase,
    private val fetchCitiesUseCase: FetchCitiesUseCase,
    private val saveCitiesUseCase: SaveCitiesUseCase,
    private val loadCitiesUseCase: LoadCitiesUseCase,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState: StateFlow<UiState> = _uiState

    private var currentPage = 0
    private var isLastPage = false

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _allCities = MutableStateFlow<List<CityPresentable>>(emptyList())
    private val _filteredCities = MutableStateFlow<List<CityPresentable>>(emptyList())
    val cities: StateFlow<List<CityPresentable>> = _filteredCities

    init {
        loadCities()
        observeSearchQuery()
    }

    private fun loadCities() {
        viewModelScope.launch {
            try {
                val isTableEmpty = validatePersistedDataUseCase.execute()
                if (isTableEmpty) {
                    fetchCitiesFromApi()
                } else {
                    loadCitiesFromDatabase()
                }
            } catch (e: Exception) {
                Log.e("CityViewModel", "Error loading cities", e)
            }
        }
    }

    private fun loadCitiesFromDatabase() {
        viewModelScope.launch {
            loadCitiesUseCase.execute(_searchQuery.value, PAGE_SIZE, currentPage * PAGE_SIZE)
                .collect { cities ->
                    if (cities.size < PAGE_SIZE) {
                        isLastPage = true
                    }
                    _allCities.value = (_allCities.value + cities).distinctBy { it.id }
                    _filteredCities.value = filterCities(_searchQuery.value, _allCities.value)
                    _uiState.value = if (_filteredCities.value.isNotEmpty()) {
                        UiState.Success(_filteredCities.value)
                    } else {
                        UiState.Empty
                    }
                }
        }
    }

    private fun fetchCitiesFromApi() {
        viewModelScope.launch {
            fetchCitiesUseCase.execute().collect { citiesFromApi ->
                saveCitiesUseCase.execute(citiesFromApi)
                currentPage = 0
                isLastPage = false
                loadCitiesFromDatabase()
            }
        }
    }

    private fun observeSearchQuery() {
        viewModelScope.launch {
            _searchQuery.collect { query ->
                resetPagination()
                _filteredCities.value = filterCities(query, _allCities.value)
                if (_filteredCities.value.isEmpty() && !isLastPage) {
                    loadNextPage()
                }
            }
        }
    }

    private fun filterCities(query: String, cities: List<CityPresentable>): List<CityPresentable> {
        return if (query.isBlank()) cities
        else cities.filter { it.name.contains(query, ignoreCase = true) }
    }

    fun updateSearchQuery(query: String) {
        if (_searchQuery.value != query) {
            _searchQuery.value = query
            resetPagination()
            _filteredCities.value = filterCities(query, _allCities.value)
            if (_filteredCities.value.isEmpty() && !isLastPage) {
                loadNextPage()
            }
        }
    }

    fun toggleFavorite(city: CityPresentable) {
        viewModelScope.launch {
            val id = city.id
            val isFavorite = !city.isFavorite

            updateFavoriteUseCase.execute(id, isFavorite)

            _allCities.value = _allCities.value.map { existingCity ->
                if (existingCity.id == id) {
                    existingCity.copy(isFavorite = isFavorite)
                } else {
                    existingCity
                }
            }
            _filteredCities.value = filterCities(_searchQuery.value, _allCities.value)
        }
    }

    fun loadNextPage() {
        if (!isLastPage) {
            currentPage++
            viewModelScope.launch {
                loadCitiesUseCase.execute(_searchQuery.value, PAGE_SIZE, currentPage * PAGE_SIZE)
                    .collect { newCities ->
                        if (newCities.size < PAGE_SIZE) {
                            isLastPage = true
                        }
                        _allCities.value = (_allCities.value + newCities).distinctBy { it.id }
                        _filteredCities.value = filterCities(_searchQuery.value, _allCities.value)
                    }
            }
        }
    }

    private fun resetPagination() {
        currentPage = 0
        isLastPage = false
    }
}