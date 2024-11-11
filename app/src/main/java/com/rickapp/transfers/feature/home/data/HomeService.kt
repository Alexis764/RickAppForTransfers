package com.rickapp.transfers.feature.home.data

import retrofit2.Response
import retrofit2.http.GET

interface HomeService {

    @GET("character")
    suspend fun getAllCharacter(): Response<AllCharacterResponse>

}