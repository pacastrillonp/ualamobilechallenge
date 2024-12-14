package co.pacastrillonp.ualamobilechallenge.ui.navigation

sealed class Routes(val name: String) {
    data object CityList : Routes("city_list")
    data object CityMap : Routes("city_map")
    data object Loading: Routes("loading")
}
