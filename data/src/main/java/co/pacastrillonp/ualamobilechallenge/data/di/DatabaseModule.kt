package co.pacastrillonp.ualamobilechallenge.data.di

import android.content.Context
import androidx.room.Room
import co.pacastrillonp.ualamobilechallenge.data.persistence.AppDatabase
import co.pacastrillonp.ualamobilechallenge.data.persistence.daos.CityDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun todoDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            context = appContext,
            klass = AppDatabase::class.java,
            name = "AppDatabase.db"
        ).build()
    }

    @Provides
    fun taskDaoProvider(appDatabase : AppDatabase): CityDao
            = appDatabase.cityDao()

}