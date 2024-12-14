package co.pacastrillonp.ualamobilechallenge.common.model

data class CityPresentable(
    val name: String,
    val country: String,
    val lat: Double,
    val lon: Double,
    var isFavorite: Boolean = false
)