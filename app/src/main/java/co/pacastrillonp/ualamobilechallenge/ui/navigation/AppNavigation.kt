package co.pacastrillonp.ualamobilechallenge.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import co.pacastrillonp.ualamobilechallenge.common.util.Constants.Routes.CITY
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
    val uiState by cityViewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = Routes.Loading.name) {
        composable(Routes.Loading.name) {
            when (uiState) {
                is UiState.Loading -> LoadingScreen()
                is UiState.Success -> {
                    LaunchedEffect(Unit) {
                        navController.navigate(Routes.CityList.name) {
                            popUpTo(Routes.Loading.name) { inclusive = true }
                        }
                    }
                }
                is UiState.Empty -> {
                    LoadingScreen()
                }
            }
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
            val latitude = backStackEntry.arguments?.getFloat(LATITUDE) ?: 0f
            val longitude = backStackEntry.arguments?.getFloat(LONGITUDE) ?: 0f
            CityMapScreen(latitude = latitude, longitude = longitude)
        }
    }
}