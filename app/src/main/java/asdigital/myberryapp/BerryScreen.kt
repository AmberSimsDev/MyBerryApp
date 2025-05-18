package asdigital.myberryapp

import android.graphics.fonts.FontStyle
import android.health.connect.datatypes.WeightRecord
import android.util.Log
import androidx.compose.foundation.Image
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
import asdigital.myberryapp.ui.MainViewModel
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter

@Composable
fun pokeScreen(modifier: Modifier = Modifier) {
    val berryViewModel: MainViewModel = viewModel()
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
                berryScreen(pokeBerries = viewState.list)
            } //Display categories
        }
    }
}

//Has the list of each berry item
@Composable
fun berryScreen(pokeBerries: List<Berry>) {
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(pokeBerries) { berry ->
            berryItemView(berryItem = berry)
        }
    }

}

//How each item looks
@Composable
fun berryItemView(berryItem: Berry) {
    Column(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Log.w(">>", "sprite URL: ${getBerrySpriteUrl(berryItem.name)}")
        AsyncImage(
            model = getBerrySpriteUrl(berryItem.name)
            ,
            contentDescription = null,
            modifier = Modifier
                .wrapContentSize()
                .aspectRatio(2f).padding(top = 30.dp)
        )


        Text(
            text = berryItem.name,
            color = Color.White,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }

}