package co.pacastrillonp.ualamobilechallenge.ui.navigation

import co.pacastrillonp.ualamobilechallenge.common.presentable.CityPresentable

sealed class UiState {
    data object Loading : UiState()
    data object Empty : UiState()
    data class Success(val cities: List<CityPresentable>) : UiState()
}