package co.pacastrillonp.ualamobilechallenge.common.mappers

import co.pacastrillonp.ualamobilechallenge.common.entities.CityEntity
import co.pacastrillonp.ualamobilechallenge.common.presentable.CityPresentable
import org.junit.Assert.assertEquals
import org.junit.Test

class CityEntityToPresentableTest {

    @Test
    fun `test cityEntityToPresentable with non-empty list`() {
        val cityEntities = listOf(
            CityEntity(1, "New York", "US", 40.7128, -74.0060, false),
            CityEntity(2, "Los Angeles", "US", 34.0522, -118.2437, true),
            CityEntity(3, "Chicago", "US", 41.8781, -87.6298, false)
        )

        val expected = listOf(
            CityPresentable(1, "New York", "US", -74.0060, 40.7128, false),
            CityPresentable(2, "Los Angeles", "US", -118.2437, 34.0522, true),
            CityPresentable(3, "Chicago", "US", -87.6298, 41.8781, false)
        )

        val result = cityEntities.cityEntityToPresentable()

        assertEquals(expected, result)
    }

    @Test
    fun `test cityEntityToPresentable with empty list`() {
        val cityEntities = emptyList<CityEntity>()

        val expected = emptyList<CityPresentable>()

        val result = cityEntities.cityEntityToPresentable()

        assertEquals(expected, result)
    }

}