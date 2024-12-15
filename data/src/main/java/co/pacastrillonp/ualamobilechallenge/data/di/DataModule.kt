package co.pacastrillonp.ualamobilechallenge.data.di

import co.pacastrillonp.ualamobilechallenge.data.implementation.ApiRepositoryImpl
import co.pacastrillonp.ualamobilechallenge.data.implementation.CityDaoProviderRepositoryImpl
import co.pacastrillonp.ualamobilechallenge.data.network.ApiService
import co.pacastrillonp.ualamobilechallenge.data.persistence.daos.CityDao
import co.pacastrillonp.ualamobilechallenge.domain.repository.ApiRepository
import co.pacastrillonp.ualamobilechallenge.domain.repository.CityDaoProviderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideApiRepository(apiService: ApiService): ApiRepository {
        return ApiRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCityDaoProviderRepository(cityDao: CityDao): CityDaoProviderRepository {
        return CityDaoProviderRepositoryImpl(cityDao)
    }

}