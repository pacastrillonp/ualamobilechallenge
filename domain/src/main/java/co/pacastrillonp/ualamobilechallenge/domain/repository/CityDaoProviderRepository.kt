package co.pacastrillonp.ualamobilechallenge.domain.repository

import co.pacastrillonp.ualamobilechallenge.common.entities.CityEntity
import co.pacastrillonp.ualamobilechallenge.common.presentable.CityPresentable
import kotlinx.coroutines.flow.Flow

interface CityDaoProviderRepository {
    suspend fun insertAll(cities: List<CityEntity>)
    suspend fun isCitiesTableEmpty(): Boolean
    fun loadCities(prefix: String?, limit: Int, offset: Int): Flow<List<CityPresentable>>
    suspend fun updateCityById(id: Int, isFavorite: Boolean)
}