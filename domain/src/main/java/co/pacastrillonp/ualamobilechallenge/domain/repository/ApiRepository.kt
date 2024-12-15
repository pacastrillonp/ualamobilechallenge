package co.pacastrillonp.ualamobilechallenge.domain.repository

import co.pacastrillonp.ualamobilechallenge.common.network.CityResponse
import kotlinx.coroutines.flow.Flow

interface ApiRepository {
    fun getCities(): Flow<List<CityResponse>>
}