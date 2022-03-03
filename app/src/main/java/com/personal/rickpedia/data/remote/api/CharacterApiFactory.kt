package com.personal.rickpedia.data.remote.api

import com.google.gson.GsonBuilder
import com.personal.rickpedia.domain.character.AllCharacterResult
import com.personal.rickpedia.domain.character.Character
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiFactory {
    companion object {
        private const val FETCH_ALL_CHARACTERS = "character"
        private const val FETCH_CHARACTER = "character/{id}"

        fun newInstance(url: String): CharacterApiFactory {
            val retrofit = getRetrofit(url)
            return retrofit.create(CharacterApiFactory::class.java)
        }

        private fun getRetrofit(url: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }


    @GET(FETCH_ALL_CHARACTERS)
    suspend fun fetchAllCharacters(
        @Query("page") page: Int
    ): AllCharacterResult

    @GET(FETCH_CHARACTER)
    suspend fun fetchCharacter(
        @Path("id") id: Int
    ): Character
}