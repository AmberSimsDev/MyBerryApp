package asdigital.myberryapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import asdigital.myberryapp.data.BerryDetail
import asdigital.myberryapp.data.Firmness
import asdigital.myberryapp.data.Flavor
import asdigital.myberryapp.data.Flavors
import asdigital.myberryapp.data.Item
import asdigital.myberryapp.data.NaturalGiftType
import asdigital.myberryapp.data.remote.PokemonApiService
import asdigital.myberryapp.data.remote.pokeService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BerryDetailViewModel : ViewModel() {

    private val _berryDetailState = MutableStateFlow<BerryDetail?>(null)
    val berryDetailState: StateFlow<BerryDetail?> = _berryDetailState

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun load(berryName: String) {
        _berryDetailState.value = BerryDetail(
            id = 0,
            name = berryName,
            growthtime = 10,
            maxharvest = 10,
            naturalgiftpower = 10,
            size = 10,
            smoothness = 10,
            soildryness = 10,
            firmness = Firmness(name = "bestName", url = "url"),
            flavors = Flavors(potency = 10, Flavor(name = "nameString", url = "besturl")),
            item = Item(name = "bestName", url = "url"),
            naturalgifttype = NaturalGiftType(name = "bestName", url = "url")
        )

    }

    suspend fun fetchBerryDetails(berryName: String) {
        val berryResponse = pokeService.getPokeBerries()
        val allBerryDetail = mutableListOf<BerryDetail>()


        for (berryResult in berryResponse.results) {
            try {
                val berryDetail = pokeService.getBerryDetail("berry/$berryName")
                allBerryDetail.add(berryDetail)
            } catch (e: Exception) {
                // Handle potential errors for individual berry fetches
                println("Error fetching details for $berryName: ${e.message}:")
            }
        }
    }
}