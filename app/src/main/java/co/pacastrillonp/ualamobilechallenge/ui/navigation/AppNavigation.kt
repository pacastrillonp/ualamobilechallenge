package co.pacastrillonp.ualamobilechallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.pacastrillonp.ualamobilechallenge.ui.screens.CityAndMapScreen
import co.pacastrillonp.ualamobilechallenge.ui.screens.CityListScreen
import co.pacastrillonp.ualamobilechallenge.ui.screens.CityMapScreen
import co.pacastrillonp.ualamobilechallenge.ui.screens.CityViewModel
import co.pacastrillonp.ualamobilechallenge.ui.screens.LoadingScreen

@Composable
fun AppNavigation(cityViewModel: CityViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Loading.name) {
        composable(Routes.Loading.name) {
            LoadingScreen()
        }
        composable(Routes.CityList.name) {
            if (isLandscape()) {
                CityAndMapScreen(cityViewModel)
            } else {
                CityListScreen(navController, cityViewModel)
            }
        }
        composable(
            "${Routes.CityMap.name}/{city}/{lat}/{lon}",
            arguments = listOf(
                navArgument("city") { type = NavType.StringType },
                navArgument("lat") { type = NavType.FloatType },
                navArgument("lon") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val lat = backStackEntry.arguments?.getFloat("lat") ?: 0f
            val lon = backStackEntry.arguments?.getFloat("lon") ?: 0f
            CityMapScreen(lat = lat, lon = lon)
        }
    }
}