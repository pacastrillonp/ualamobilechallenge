package co.pacastrillonp.ualamobilechallenge.domain.usecases

import co.pacastrillonp.ualamobilechallenge.domain.repository.CityDaoProviderRepository
import javax.inject.Inject

class ValidatePersistedDataUseCase  @Inject constructor(
    private val cityDaoProviderRepository: CityDaoProviderRepository
) {
    suspend fun execute(): Boolean {
        return cityDaoProviderRepository.isCitiesTableEmpty()
    }
}