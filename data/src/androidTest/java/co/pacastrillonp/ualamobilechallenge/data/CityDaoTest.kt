package co.pacastrillonp.ualamobilechallenge.data
import androidx.room.Room
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import co.pacastrillonp.ualamobilechallenge.common.entities.CityEntity
import co.pacastrillonp.ualamobilechallenge.data.persistence.AppDatabase
import co.pacastrillonp.ualamobilechallenge.data.persistence.daos.CityDao
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

@RunWith(AndroidJUnit4ClassRunner::class)
class CityDaoTest {
    private lateinit var cityDao: CityDao
    private lateinit var appDatabase: AppDatabase

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().context
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        cityDao = appDatabase.cityDao()
    }

    @After
    fun closeDb() {
        appDatabase.close()
    }

    @Test
    fun should_return_city_entity_list() = runBlocking {
        val firstCityEntity = CityEntity(1, "New York", "US", 40.7128, -74.0060, false)
        val secondCityEntity = CityEntity(2, "Los Angeles", "US", 34.0522, -118.2437, false)
        val thirdCityEntity = CityEntity(3, "Chicago", "US", 41.8781, -87.6298, false)

        val expectedList = listOf(firstCityEntity, secondCityEntity, thirdCityEntity)

        cityDao.insertAll(listOf(firstCityEntity, secondCityEntity, thirdCityEntity))

        val resultList = cityDao.searchCitiesByPrefixWithPagination("", 10, 0).first().sortedBy { it.id }

        Assert.assertEquals(expectedList, resultList)
    }

    @Test
    fun should_return_empty_city_entity_list() = runBlocking {
        val expectedList = emptyList<CityEntity>()

        val resultList = cityDao.searchCitiesByPrefixWithPagination("", 10, 0).first()

        Assert.assertEquals(expectedList, resultList)
    }
}
