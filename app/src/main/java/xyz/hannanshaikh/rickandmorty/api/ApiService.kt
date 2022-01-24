package xyz.hannanshaikh.rickandmorty.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import xyz.hannanshaikh.rickandmorty.data.model.CharacterResponse

interface ApiService {

    @GET("api/character")
    suspend fun getCharacters(@Query("page") page: Int) : Response<CharacterResponse>
}