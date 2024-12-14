package co.pacastrillonp.ualamobilechallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.pacastrillonp.ualamobilechallenge.common.util.Constants.Routes.CITY
import co.pacastrillonp.ualamobilechallenge.common.util.Constants.Routes.DEFAULT_ORIENTATION
import co.pacastrillonp.ualamobilechallenge.common.util.Constants.Routes.LATITUDE
import co.pacastrillonp.ualamobilechallenge.common.util.Constants.Routes.LONGITUDE
import co.pacastrillonp.ualamobilechallenge.ui.screens.CityListLandscapeScreen
import co.pacastrillonp.ualamobilechallenge.ui.screens.CityListPortraitScreen
import co.pacastrillonp.ualamobilechallenge.ui.screens.CityMapScreen
import co.pacastrillonp.ualamobilechallenge.ui.screens.CityViewModel
import co.pacastrillonp.ualamobilechallenge.ui.screens.LoadingScreen

@Composable
fun AppNavigation(cityViewModel: CityViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.CityList.name) {
        composable(Routes.Loading.name) {
            LoadingScreen()
        }
        composable(Routes.CityList.name) {
            if (isPortrait()) {
                CityListPortraitScreen(navController, cityViewModel)
            } else {
                CityListLandscapeScreen(cityViewModel)
            }
        }
        composable(
            "${Routes.CityMap.name}/{$CITY}/{$LATITUDE}/{$LONGITUDE}",
            arguments = listOf(
                navArgument(CITY) { type = NavType.StringType },
                navArgument(LATITUDE) { type = NavType.FloatType },
                navArgument(LONGITUDE) { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val latitude = backStackEntry.arguments?.getFloat(LATITUDE) ?: DEFAULT_ORIENTATION
            val longitude = backStackEntry.arguments?.getFloat(LONGITUDE) ?: DEFAULT_ORIENTATION
            CityMapScreen(latitude = latitude, longitude = longitude)
        }
    }
}