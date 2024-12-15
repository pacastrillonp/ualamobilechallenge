package co.pacastrillonp.ualamobilechallenge.common.mappers

import co.pacastrillonp.ualamobilechallenge.common.entities.CityEntity
import co.pacastrillonp.ualamobilechallenge.common.network.CityResponse
import co.pacastrillonp.ualamobilechallenge.common.presentable.CityPresentable

fun List<CityResponse>.citiesResponseToEntityMapper(): List<CityEntity> {
    return this.map {
        CityEntity(
            id = it.id.toInt(),
            name = it.name,
            country = it.country,
            latitude = it.coordinates.latitude,
            longitude = it.coordinates.longitude
        )
    }
}