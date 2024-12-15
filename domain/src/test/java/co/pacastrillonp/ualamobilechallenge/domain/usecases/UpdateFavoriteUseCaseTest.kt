package co.pacastrillonp.ualamobilechallenge.domain.usecases

import co.pacastrillonp.ualamobilechallenge.domain.repository.CityDaoProviderRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class UpdateFavoriteUseCaseTest {

    private val cityDaoProviderRepository = mock<CityDaoProviderRepository>()
    private val underTest = UpdateFavoriteUseCase(cityDaoProviderRepository)

    @Test
    fun `test execute updates city favorite status`() {
        runBlocking {
            val cityId = 1
            val isFavorite = true

            underTest.execute(cityId, isFavorite)

            verify(cityDaoProviderRepository).updateCityById(cityId, isFavorite)
        }
    }

    @Test
    fun `test execute updates city favorite status to false`() {
        runBlocking {
            val cityId = 2
            val isFavorite = false

            underTest.execute(cityId, isFavorite)

            verify(cityDaoProviderRepository).updateCityById(cityId, isFavorite)
        }
    }
}