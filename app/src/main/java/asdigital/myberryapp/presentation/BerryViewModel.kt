package asdigital.myberryapp.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import asdigital.myberryapp.data.remote.model.Berry
import asdigital.myberryapp.data.remote.api.pokeService
import asdigital.myberryapp.data.remote.model.BerryDTO
import kotlinx.coroutines.launch


class BerryViewModel : ViewModel() {
    private val _berryState = mutableStateOf(PokeState())
    val berryState: State<PokeState> = _berryState

    init {
        Log.w(">>", "init")
        fetchBerryState()
    }

    private fun fetchBerryState() {
        viewModelScope.launch {
            try {
                Log.w(">>", "launch")
                val response = pokeService.getPokeBerries()
                Log.w(">>", "response $response")
                _berryState.value = _berryState.value.copy(
                    list = response.results,
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                _berryState.value = _berryState.value.copy(
                    loading = false,
                    error = "Error: ${e.message}"
                )
                Log.e(">>", "Error fetching berries", e)
            }
        }
    }

    data class PokeState(
        var loading: Boolean = true,
        var list: List<BerryDTO> = emptyList(),
        var error: String? = null
    )
   /* val _vmState = MutableStateFlow<State>(State.Idle)
    val vmState: StateFlow<State> = _vmState.asStateFlow()

    sealed interface State {
        object Idle: State
        object Loading: State
        data class Content(val data:List<Berry>)
        object Error: State
    }*/
}