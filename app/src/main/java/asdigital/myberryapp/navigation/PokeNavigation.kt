package navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

import asdigital.myberryapp.presentation.berryDetailScreen
import asdigital.myberryapp.presentation.pokeScreen

@Composable
fun pokeNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.pokeScreen, builder = {
        composable(Routes.pokeScreen) {
            pokeScreen(modifier = Modifier, navController = navController)
        }
        composable(Routes.berryDetailScreen,
            arguments = listOf(navArgument("berryName") { type = NavType.StringType })
        ) { backStackEntry ->
            val berryName = backStackEntry.arguments?.getString("berryName")
            //FETCH THE INFO FOR EACH BERRY NAME
            berryDetailScreen(navController = navController, berryName = berryName)

        }
    }

    )
}


/* private class Screen (val route:String){
    var BerryScreen: Screen ("pokeScreen")
    var BerryDetailScreen:Screen("berryDetailScreen")

}
*/