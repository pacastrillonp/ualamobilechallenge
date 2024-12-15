package co.pacastrillonp.ualamobilechallenge.domain.usecases

import co.pacastrillonp.ualamobilechallenge.common.presentable.CityPresentable
import co.pacastrillonp.ualamobilechallenge.domain.repository.CityDaoProviderRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoadCitiesUseCase @Inject constructor(
    private val cityDaoProviderRepository: CityDaoProviderRepository
) {
    fun execute(prefix: String?, limit: Int, offset: Int): Flow<List<CityPresentable>> {
        return cityDaoProviderRepository.loadCities(prefix, limit, offset)
    }
}