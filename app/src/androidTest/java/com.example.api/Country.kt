package com.example.esqueletoejercicioapi

import com.google.gson.annotations.SerializedName

data class Country(
    var name : String,

    @SerializedName("alpha2Code")var alpha2Code: String,
    @SerializedName("alpha3Code")var alpha3Code: String,
    @SerializedName("capital") val capital: String,
    @SerializedName("region")  var region: String,
    @SerializedName("subregion") var subregion: String,
    @SerializedName("population")var population: Int,
    @SerializedName("demonym")var demonym: String,
    @SerializedName("area") var area: Float,
    @SerializedName("gini") var gini: Float){

    override fun toString(): String {
        return "El pais $name, con codigo $alpha2Code, tiene una capital que es $capital,y pertenece a le region de $region\n"
    }
}

