package co.pacastrillonp.ualamobilechallenge.data.network

import co.pacastrillonp.ualamobilechallenge.common.network.CityResponse
import co.pacastrillonp.ualamobilechallenge.common.util.Constants.Api.CITIES
import retrofit2.http.GET

interface ApiService {
    @GET(CITIES)
    suspend fun getCities(): List<CityResponse>
}