package co.pacastrillonp.ualamobilechallenge.ui.navigation

import co.pacastrillonp.ualamobilechallenge.common.util.Constants.Routes.CITY_LIST
import co.pacastrillonp.ualamobilechallenge.common.util.Constants.Routes.CITY_MAP
import co.pacastrillonp.ualamobilechallenge.common.util.Constants.Routes.LOADING

sealed class Routes(val name: String) {
    data object CityList : Routes(CITY_LIST)
    data object CityMap : Routes(CITY_MAP)
    data object Loading: Routes(LOADING)
}
