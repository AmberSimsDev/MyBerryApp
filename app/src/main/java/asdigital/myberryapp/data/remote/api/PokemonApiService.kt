package asdigital.myberryapp.data.remote.api

import asdigital.myberryapp.data.remote.model.BerryDetail
import asdigital.myberryapp.data.remote.model.BerryResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private val retrofit = Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
    .addConverterFactory(GsonConverterFactory.create()).build()

val pokeService = retrofit.create(PokemonApiService::class.java)


interface PokemonApiService{
    @GET("berry")
    suspend fun getPokeBerries(): BerryResponse

    @GET("berry/{berryName}/")
    suspend fun getBerryDetail(@Path ("berryName") berryName: String): BerryDetail
}

//@GET("berry/{id}/")
//suspend fun getBerryDetail(@Path("id") berryName: Int): BerryDetail

//Original
//@GET("")
//suspend fun getBerryDetail(@Url berryUrl: String): BerryDetail

//@GET("berry/{name}/")
//suspend fun getBerryDetail(@Path("name") showBerries: String): BerryDetail
//}