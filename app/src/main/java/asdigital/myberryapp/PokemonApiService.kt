package asdigital.myberryapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val retrofit = Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
    .addConverterFactory(GsonConverterFactory.create()).build()

val pokeService = retrofit.create(PokemonApiService::class.java)



interface PokemonApiService{
    @GET("berry")
    suspend fun getPokeBerries():BerryResponse
}

