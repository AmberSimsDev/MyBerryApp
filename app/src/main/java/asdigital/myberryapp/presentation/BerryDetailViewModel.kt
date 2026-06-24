package asdigital.myberryapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import asdigital.myberryapp.data.remote.model.BerryDetail
import asdigital.myberryapp.data.remote.api.pokeService
import asdigital.myberryapp.data.remote.model.BerryDetailDTO
import asdigital.myberryapp.data.remote.model.BerryDetailResponseDTO

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BerryDetailViewModel : ViewModel() {

    private val _berryDetailState = MutableStateFlow<BerryDetailDTO?>(null)
    val berryDetailState: StateFlow<BerryDetailDTO?> = _berryDetailState

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

//    fun load(berryName: String) {
//        _berryDetailState.value = BerryDetail(
//            id = 0,
//            name = berryName,
//            growthtime = 10,
//            maxharvest = 10,
//            naturalgiftpower = 10,
//            size = 10,
//            smoothness = 10,
//            soildryness = 10,
//            firmness = Firmness(name = "bestName", url = "url"),
//            flavors = Flavors(potency = 10, Flavor(name = "nameString", url = "besturl")),
//            item = Item(name = "bestName", url = "url"),
//            naturalgifttype = NaturalGiftType(name = "bestName", url = "url")


    suspend fun fetchBerryDetails(berryName: String) {

        _isLoading.value = true // Set loading state to true
        _errorMessage.value = null // Clear any previous error message

        viewModelScope.launch {
            try {
                // CORRECTED LINE: Pass the actual berryName argument
                Log.d("BerryDetailVM", "Attempting to fetch details for: $berryName")
                val berryDetail = pokeService.getBerryDetail(berryName)

                // CORRECTED LINE: Assign the fetched data to the state
                _berryDetailState.value = berryDetail
                Log.d("BerryDetailVM", "Successfully fetched details for: $berryName")

            } catch (e: Exception) {
                // Handle potential errors for individual berry fetches
                val errorMsg = "Error fetching details for $berryName: ${e.message ?: "Unknown error"}"
                Log.e("BerryDetailVM", errorMsg, e) // Use Log.e to see full stack trace
                _errorMessage.value = errorMsg // Set the error message for UI
                _berryDetailState.value = null // Clear previous data on error
            } finally {
                _isLoading.value = false // Always set loading to false after completion (success or error)
            }
        }
    }
}


//UNUSED
//suspend fun fetchBerryDetails(berryName: String) {
//    _isLoading.value = true // Set loading state to true
//    _errorMessage.value = null // Clear any previous error message
//
//viewModelScope.launch {
//    val berryDetail = pokeService.getBerryDetail("{berryName}")
//    try {
//
//         _berryDetailState.value = BerryDetail( /* insert the json data mapped here*/)
//    } catch (e: Exception) {
//        // Handle potential errors for individual berry fetches
//        println("Error fetching details for $berryName: ${e.message}:")
//    }
//
//}
//
//}


//data class PokeDetailState(
//    var loading: Boolean = true,
//    var list: List<BerryDetail> = emptyList(),
//    var error: String? = null
//)