package co.pacastrillonp.ualamobilechallenge.domain.usecases

import co.pacastrillonp.ualamobilechallenge.common.presentable.CityPresentable
import co.pacastrillonp.ualamobilechallenge.domain.repository.CityDaoProviderRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class LoadCitiesUseCaseTest {

    private val cityDaoProviderRepository = mock<CityDaoProviderRepository>()
    private val underTest = LoadCitiesUseCase(cityDaoProviderRepository)

    @Test
    fun `test execute returns city presentables`() {
        runBlocking {
            val cityPresentables = listOf(
                CityPresentable(1, "New York", "US", 40.7128, -74.0060, false),
                CityPresentable(2, "Los Angeles", "US", 34.0522, -118.2437, true),
                CityPresentable(3, "Chicago", "US", 41.8781, -87.6298, false)
            )

            whenever(cityDaoProviderRepository.loadCities(null, 10, 0)) doReturn flowOf(cityPresentables)

            val result: List<CityPresentable> = underTest.execute(null, 10, 0).toList().flatten()

            assertEquals(cityPresentables, result)
            verify(cityDaoProviderRepository).loadCities(null, 10, 0)
        }
    }

    @Test
    fun `test execute with empty list`() {
        runBlocking {
            whenever(cityDaoProviderRepository.loadCities(null, 10, 0)) doReturn flowOf(emptyList())

            val result: List<CityPresentable> = underTest.execute(null, 10, 0).toList().flatten()

            assertEquals(emptyList<CityPresentable>(), result)
            verify(cityDaoProviderRepository).loadCities(null, 10, 0)
        }
    }
}