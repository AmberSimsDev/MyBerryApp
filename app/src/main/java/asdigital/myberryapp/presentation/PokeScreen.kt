package asdigital.myberryapp.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import asdigital.myberryapp.data.remote.model.Berry
import asdigital.myberryapp.data.remote.api.getBerrySpriteUrl
import coil.compose.AsyncImage


@Composable
fun pokeScreen(modifier: Modifier, navController : NavController) {
    val berryViewModel: BerryViewModel = viewModel()
    val viewState by berryViewModel.berryState
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewState.loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            viewState.error != null -> {
                Text(text = "Error")
            }

            else -> {
                berryScreen(pokeBerries = viewState.list, navController = navController)
            } //Display categories
        }
    }
}

//Has the list of each berry item
@Composable
fun berryScreen(pokeBerries: List<Berry>, navController: NavController) {
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(pokeBerries) { berry ->
            berryItemView(berryItem = berry, navController = navController)
        }
    }

}

//How each item looks
@Composable
fun berryItemView(berryItem: Berry, navController: NavController ) {
    Column(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        //Log.w(">>", "sprite URL: ${getBerrySpriteUrl(berryItem.name)}")
        AsyncImage(
            model = getBerrySpriteUrl(berryItem.name),
            contentDescription = null,
            modifier = Modifier
                .wrapContentSize().aspectRatio(2f)
                .padding(top = 30.dp)
                .clickable{ navController.navigate("berryDetailScreen/${berryItem.name}")
                }
        )
        Text(
            text = berryItem.name,
            color = Color.White,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }

}




