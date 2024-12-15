package co.pacastrillonp.ualamobilechallenge.data.implementation

import co.pacastrillonp.ualamobilechallenge.common.network.CityResponse
import co.pacastrillonp.ualamobilechallenge.data.network.ApiService
import co.pacastrillonp.ualamobilechallenge.domain.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Singleton

@Singleton
class ApiRepositoryImpl(private val apiService: ApiService) : ApiRepository {

    override fun getCities(): Flow<List<CityResponse>> {
        return flow {
            val response = apiService.getCities()
            emit(response)
        }
    }

}