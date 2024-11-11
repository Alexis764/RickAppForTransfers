package com.rickapp.transfers.feature.home.data

import com.google.gson.annotations.SerializedName

data class AllCharacterResponse(
    @SerializedName("results") val results: List<Character>
)


data class Character(
    @SerializedName("name") val name: String = "",
    @SerializedName("species") val species: String = "",
    @SerializedName("status") val status: String = "",
    @SerializedName("gender") val gender: String = "",
    @SerializedName("origin") val origin: Origin = Origin(""),
    @SerializedName("image") val image: String = ""
)


data class Origin(
    @SerializedName("name") val name: String
)