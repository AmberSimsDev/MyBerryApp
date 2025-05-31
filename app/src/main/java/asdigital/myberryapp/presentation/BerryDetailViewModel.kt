package asdigital.myberryapp.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import asdigital.myberryapp.data.BerryDetail
import asdigital.myberryapp.data.remote.PokemonApiService
import asdigital.myberryapp.data.remote.pokeService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BerryDetailViewModel (pokeService: PokemonApiService): ViewModel(){

    private val _berryDetailState = MutableStateFlow<BerryDetail?>(null)
    val berryDetailState: StateFlow<BerryDetail?> = _berryDetailState

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading


    suspend fun fetchBerryDetails(berryName:String) {
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