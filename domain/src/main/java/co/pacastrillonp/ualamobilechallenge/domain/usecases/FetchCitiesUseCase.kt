package co.pacastrillonp.ualamobilechallenge.domain.usecases

import co.pacastrillonp.ualamobilechallenge.common.entities.CityEntity
import co.pacastrillonp.ualamobilechallenge.common.mappers.citiesResponseToEntityMapper
import co.pacastrillonp.ualamobilechallenge.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchCitiesUseCase @Inject constructor(
    private val apiRepository: ApiRepository
) {
    fun execute(): Flow<List<CityEntity>> {
        val cities = apiRepository.getCities()
        return cities.map {
            it.citiesResponseToEntityMapper()
        }
    }
}