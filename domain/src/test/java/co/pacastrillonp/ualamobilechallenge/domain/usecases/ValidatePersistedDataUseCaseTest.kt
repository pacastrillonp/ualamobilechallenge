package co.pacastrillonp.ualamobilechallenge.domain.usecases

import co.pacastrillonp.ualamobilechallenge.domain.repository.CityDaoProviderRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ValidatePersistedDataUseCaseTest {

    private val cityDaoProviderRepository = mock<CityDaoProviderRepository>()
    private val underTest = ValidatePersistedDataUseCase(cityDaoProviderRepository)

    @Test
    fun `test execute returns true when cities table is empty`() {
        runBlocking {
            whenever(cityDaoProviderRepository.isCitiesTableEmpty()).thenReturn(true)

            val result = underTest.execute()

            assertEquals(true, result)
        }
    }

    @Test
    fun `test execute returns false when cities table is not empty`() {
        runBlocking {
            whenever(cityDaoProviderRepository.isCitiesTableEmpty()).thenReturn(false)

            val result = underTest.execute()

            assertEquals(false, result)
        }
    }
}