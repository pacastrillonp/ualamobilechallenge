package co.pacastrillonp.ualamobilechallenge.common.mappers

import co.pacastrillonp.ualamobilechallenge.common.entities.CityEntity
import co.pacastrillonp.ualamobilechallenge.common.presentable.CityPresentable

fun List<CityEntity>.cityEntityToPresentable(): List<CityPresentable> {
    return this.map {
        CityPresentable(
            id = it.id,
            name = it.name,
            country = it.country,
            latitude = it.latitude,
            longitude = it.longitude,
            isFavorite = it.isFavorite
        )
    }
}