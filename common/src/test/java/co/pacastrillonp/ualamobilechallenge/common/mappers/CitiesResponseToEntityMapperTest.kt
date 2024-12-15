package co.pacastrillonp.ualamobilechallenge.common.mappers

import co.pacastrillonp.ualamobilechallenge.common.entities.CityEntity
import co.pacastrillonp.ualamobilechallenge.common.network.CityResponse
import co.pacastrillonp.ualamobilechallenge.common.network.Coordinates
import org.junit.Assert.assertEquals
import org.junit.Test

class CitiesResponseToEntityMapperTest {

    @Test
    fun `test citiesResponseToEntityMapper with non-empty list`() {
        val cityResponses = listOf(
            CityResponse("US", "New York", 1, Coordinates(40.7128, -74.0060)),
            CityResponse("US", "Los Angeles", 2, Coordinates(34.0522, -118.2437)),
            CityResponse("US", "Chicago", 3, Coordinates(41.8781, -87.6298))
        )

        val expected = listOf(
            CityEntity(1, "New York", "US", 40.7128, -74.0060),
            CityEntity(2, "Los Angeles", "US", 34.0522, -118.2437),
            CityEntity(3, "Chicago", "US", 41.8781, -87.6298)
        )

        val result = cityResponses.citiesResponseToEntityMapper()

        assertEquals(expected, result)
    }

    @Test
    fun `test citiesResponseToEntityMapper with empty list`() {
        val cityResponses = emptyList<CityResponse>()

        val expected = emptyList<CityEntity>()

        val result = cityResponses.citiesResponseToEntityMapper()

        assertEquals(expected, result)
    }
}