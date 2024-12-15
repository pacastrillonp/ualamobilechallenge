package co.pacastrillonp.ualamobilechallenge.domain.usecases

import co.pacastrillonp.ualamobilechallenge.common.entities.CityEntity
import co.pacastrillonp.ualamobilechallenge.common.mappers.citiesResponseToEntityMapper
import co.pacastrillonp.ualamobilechallenge.common.network.CityResponse
import co.pacastrillonp.ualamobilechallenge.common.network.Coordinates
import co.pacastrillonp.ualamobilechallenge.domain.repository.ApiRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class FetchCitiesUseCaseTest {

    private val apiRepository = mock<ApiRepository>()
    private val underTest = FetchCitiesUseCase(apiRepository)

    @Test
    fun `test execute returns mapped city entities`() {
        runBlocking {
            val cityResponses = listOf(
                CityResponse("US", "New York", 1, Coordinates(40.7128, -74.0060)),
                CityResponse("US", "Los Angeles", 2, Coordinates(34.0522, -118.2437)),
                CityResponse("US", "Chicago", 3, Coordinates(41.8781, -87.6298))
            )
            val cityEntities = cityResponses.citiesResponseToEntityMapper()

            whenever(apiRepository.getCities()) doReturn flowOf(cityResponses)

            val result: List<CityEntity> = underTest.execute().toList().flatten()

            assertEquals(cityEntities, result)
            verify(apiRepository).getCities()
        }
    }

    @Test
    fun `test execute with empty list`() {
        runBlocking {
            whenever(apiRepository.getCities()) doReturn flowOf(emptyList())

            val result: List<CityEntity> = underTest.execute().toList().flatten()

            assertEquals(emptyList<CityEntity>(), result)
            verify(apiRepository).getCities()
        }
    }
}