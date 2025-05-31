package asdigital.myberryapp.data.remote

import asdigital.myberryapp.data.BerryDetail
import asdigital.myberryapp.data.BerryResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

private val retrofit = Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
    .addConverterFactory(GsonConverterFactory.create()).build()

val pokeService = retrofit.create(PokemonApiService::class.java)



interface PokemonApiService{
    @GET("berry")
    suspend fun getPokeBerries(): BerryResponse

    @GET("")
    suspend fun getBerryDetail(@Url berryUrl: String): BerryDetail
}

