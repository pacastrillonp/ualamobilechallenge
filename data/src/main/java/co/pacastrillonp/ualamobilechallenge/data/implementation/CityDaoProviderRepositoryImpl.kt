package co.pacastrillonp.ualamobilechallenge.data.implementation

import co.pacastrillonp.ualamobilechallenge.common.entities.CityEntity
import co.pacastrillonp.ualamobilechallenge.common.mappers.cityEntityToPresentable
import co.pacastrillonp.ualamobilechallenge.common.presentable.CityPresentable
import co.pacastrillonp.ualamobilechallenge.data.persistence.daos.CityDao
import co.pacastrillonp.ualamobilechallenge.domain.repository.CityDaoProviderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Singleton

@Singleton
class CityDaoProviderRepositoryImpl(private val cityDao: CityDao) : CityDaoProviderRepository {

    override suspend fun insertAll(cities: List<CityEntity>) {
        cityDao.insertAll(cities)
    }

    override suspend fun isCitiesTableEmpty(): Boolean {
        return cityDao.isTableEmpty()
    }

    override fun loadCities(prefix: String?, limit: Int, offset: Int): Flow<List<CityPresentable>> {
        return cityDao.searchCitiesByPrefixWithPagination(prefix, limit, offset)
            .map { it.cityEntityToPresentable() }
    }

    override suspend fun updateCityById(id: Int, isFavorite: Boolean) {
        cityDao.updateCityById(id, isFavorite)
    }

}