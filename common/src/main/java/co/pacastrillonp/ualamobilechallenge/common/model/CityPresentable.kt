package co.pacastrillonp.ualamobilechallenge.common.model

data class CityPresentable(
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    var isFavorite: Boolean = false
)