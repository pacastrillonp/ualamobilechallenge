package co.pacastrillonp.ualamobilechallenge.domain.usecases

import co.pacastrillonp.ualamobilechallenge.common.entities.CityEntity
import co.pacastrillonp.ualamobilechallenge.domain.repository.CityDaoProviderRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SaveCitiesUseCaseTest {

    private val cityDaoProviderRepository = mock<CityDaoProviderRepository>()
    private val underTest = SaveCitiesUseCase(cityDaoProviderRepository)

    @Test
    fun `test execute saves city entities`() {
        runBlocking {
            val cityEntities = listOf(
                CityEntity(1, "New York", "US", 40.7128, -74.0060),
                CityEntity(2, "Los Angeles", "US", 34.0522, -118.2437),
                CityEntity(3, "Chicago", "US", 41.8781, -87.6298)
            )

            underTest.execute(cityEntities)

            verify(cityDaoProviderRepository).insertAll(cityEntities)
        }
    }

    @Test
    fun `test execute with empty list`() {
        runBlocking {
            val cityEntities = emptyList<CityEntity>()

            underTest.execute(cityEntities)

            verify(cityDaoProviderRepository).insertAll(cityEntities)
        }
    }
}