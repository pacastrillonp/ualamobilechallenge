package co.pacastrillonp.ualamobilechallenge.common.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CityResponse(
    val country: String,
    val name: String,
    @Json(name = "_id") val id: Long,
    @Json(name = "coord") val coordinates: Coordinates
)
