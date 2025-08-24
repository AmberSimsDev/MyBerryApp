package asdigital.myberryapp.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import asdigital.myberryapp.data.Berry
import asdigital.myberryapp.data.BerryDetail
import asdigital.myberryapp.data.remote.getBerrySpriteUrl
import coil.compose.AsyncImage
import navigation.Routes

// navigateToDetail: (BerryDetail) -> Unit

@Composable
fun berryDetailScreen(navController: NavController, berryName: String?) {
    val viewModel: BerryDetailViewModel = viewModel()
    val detailItem by viewModel.berryDetailState.collectAsState(initial = null)
    val errorMessage by viewModel.errorMessage.collectAsState(initial = null)
    val isLoading by viewModel.isLoading.collectAsState(initial = false)

    // Launch a coroutine to fetch the berry details
    LaunchedEffect(key1 = berryName) {
        if (!berryName.isNullOrEmpty()) {
            viewModel.fetchBerryDetails(berryName)
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .clickable {
                //  navController.navigate(Routes.pokeScreen)
                navController.popBackStack() //This is a back button
            }, // Closing bracket for clickable modifier
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly
    ) { // Opening bracket for Column content
        AsyncImage(
            model = getBerrySpriteUrl("$berryName"),
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(6f)
        )
        detailItem?.let { //TODO study this more
            // LETS MAKE IT SO WE CAN SCROLL THE TEXT
            Text(
                text = formatBerryDetails(detailTextItem = it),
                textAlign = TextAlign.Justify,
                modifier = Modifier.verticalScroll(rememberScrollState())
            )
        } // Closing bracket for Column content
    }
}

//CONVERT DATA CLASS TO STRINGS
fun formatBerryDetails(detailTextItem: BerryDetail): String {
    return """
              ID: ${detailTextItem.id}
              Name: ${detailTextItem.name}
              Growth Time: ${detailTextItem.growthTime}
              Max Harvest: ${detailTextItem.maxHarvest}
              Natural Gift Power: ${detailTextItem.naturalGiftPower}
              Size: ${detailTextItem.size}
              Smoothness: ${detailTextItem.smoothness}
              Soil Dryness: ${detailTextItem.soilDryness}
              Firmness: ${detailTextItem.firmness.name}
              Flavor:${detailTextItem.flavors.firstOrNull()?.flavor?.name ?: "N/A"}
              Item: ${detailTextItem.item.name}
              Natural Gift Type: ${detailTextItem.naturalGiftType.name}
    """.trimIndent()
}
/*
val name = remember { mutableStateOf("") }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("This is the first screen", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = name.value, onValueChange = {
            newName -> name.value = newName
        })
    }
 */