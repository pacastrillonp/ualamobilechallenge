package co.pacastrillonp.ualamobilechallenge.domain.usecases

import co.pacastrillonp.ualamobilechallenge.common.entities.CityEntity
import co.pacastrillonp.ualamobilechallenge.domain.repository.CityDaoProviderRepository
import javax.inject.Inject

class SaveCitiesUseCase @Inject constructor(
    private val cityDaoProviderRepository: CityDaoProviderRepository
) {
    suspend fun execute(cities: List<CityEntity>) {
        cityDaoProviderRepository.insertAll(cities)
    }
}