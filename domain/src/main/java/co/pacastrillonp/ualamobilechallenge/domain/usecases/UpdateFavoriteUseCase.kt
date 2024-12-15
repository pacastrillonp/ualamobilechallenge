package co.pacastrillonp.ualamobilechallenge.domain.usecases

import co.pacastrillonp.ualamobilechallenge.domain.repository.CityDaoProviderRepository
import javax.inject.Inject

class UpdateFavoriteUseCase @Inject constructor(
    private val cityDaoProviderRepository: CityDaoProviderRepository
) {
    suspend fun execute(id: Int, isFavorite: Boolean) {
        cityDaoProviderRepository.updateCityById(id, isFavorite)
    }
}