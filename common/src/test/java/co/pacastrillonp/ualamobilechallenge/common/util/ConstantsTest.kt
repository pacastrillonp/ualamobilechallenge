package co.pacastrillonp.ualamobilechallenge.common.util

import org.junit.Assert.assertEquals
import org.junit.Test

class ConstantsTest {

    @Test
    fun testApiConstants() {
        assertEquals("https://gist.githubusercontent.com/hernan-uala/dce8843a8edbe0b0018b32e137bc2b3a/raw/0996accf70cb0ca0e16f9a99e0ee185fafca7af1/", Constants.Api.BASE_URL)
        assertEquals("cities.json", Constants.Api.CITIES)
    }

    @Test
    fun testRoutesConstants() {
        assertEquals("city_list", Constants.Routes.CITY_LIST)
        assertEquals("city_map", Constants.Routes.CITY_MAP)
        assertEquals("loading", Constants.Routes.LOADING)
        assertEquals("city", Constants.Routes.CITY)
        assertEquals("latitude", Constants.Routes.LATITUDE)
        assertEquals("longitude", Constants.Routes.LONGITUDE)
        assertEquals(0f, Constants.Routes.DEFAULT_ORIENTATION, 0f)
    }

    @Test
    fun testPaginationConstants() {
        assertEquals(20, Constants.Pagination.PAGE_SIZE)
    }
}