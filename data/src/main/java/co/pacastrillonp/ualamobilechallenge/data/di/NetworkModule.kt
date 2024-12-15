package co.pacastrillonp.ualamobilechallenge.data.di

import co.pacastrillonp.ualamobilechallenge.common.util.Constants.Api.BASE_URL
import co.pacastrillonp.ualamobilechallenge.data.network.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMoshiMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory {
        return MoshiConverterFactory.create(moshi)
    }

    @Provides
    @Singleton
    fun provideRetrofit(moshiConverterFactory: MoshiConverterFactory): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(moshiConverterFactory)
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

}