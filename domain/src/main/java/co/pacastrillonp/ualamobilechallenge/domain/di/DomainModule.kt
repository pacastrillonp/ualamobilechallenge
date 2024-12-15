package co.pacastrillonp.ualamobilechallenge.domain.di

import co.pacastrillonp.ualamobilechallenge.domain.repository.ApiRepository
import co.pacastrillonp.ualamobilechallenge.domain.repository.CityDaoProviderRepository
import co.pacastrillonp.ualamobilechallenge.domain.usecases.FetchCitiesUseCase
import co.pacastrillonp.ualamobilechallenge.domain.usecases.LoadCitiesUseCase
import co.pacastrillonp.ualamobilechallenge.domain.usecases.SaveCitiesUseCase
import co.pacastrillonp.ualamobilechallenge.domain.usecases.UpdateFavoriteUseCase
import co.pacastrillonp.ualamobilechallenge.domain.usecases.ValidatePersistedDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun provideValidatePersistedDataUseCase(
        cityDaoProviderRepository: CityDaoProviderRepository
    ): ValidatePersistedDataUseCase {
        return ValidatePersistedDataUseCase(
            cityDaoProviderRepository
        )
    }

    @Provides
    fun provideFetchCitiesUseCase(
        apiRepository: ApiRepository
    ): FetchCitiesUseCase {
        return FetchCitiesUseCase(
            apiRepository
        )
    }

    @Provides
    fun provideSaveCitiesUseCase(
        cityDaoProviderRepository: CityDaoProviderRepository
    ): SaveCitiesUseCase {
        return SaveCitiesUseCase(
            cityDaoProviderRepository
        )
    }

    @Provides
    fun provideLoadCitiesUseCase(
        cityDaoProviderRepository: CityDaoProviderRepository
    ): LoadCitiesUseCase {
        return LoadCitiesUseCase(
            cityDaoProviderRepository
        )
    }

    @Provides
    fun provideUpdateFavoriteUseCase(
        cityDaoProviderRepository: CityDaoProviderRepository
    ): UpdateFavoriteUseCase {
        return UpdateFavoriteUseCase(
            cityDaoProviderRepository
        )
    }

}