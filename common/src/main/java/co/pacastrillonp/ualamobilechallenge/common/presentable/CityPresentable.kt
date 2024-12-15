package co.pacastrillonp.ualamobilechallenge.common.presentable

data class CityPresentable(
    val id: Int,
    val name: String,
    val country: String,
    val latitude: Double,
    val longitude: Double,
    var isFavorite: Boolean = false
)