package com.example.listofcountriestask.data.remote

import com.example.listofcountriestask.data.model.CountriesModel
import retrofit2.http.GET

interface ApiRequest {
    @GET(ApiDetails.ENDPOINT)
    suspend fun getCountriesList(): CountriesModel
}