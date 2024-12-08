package co.pacastrillonp.ualamobilechallenge.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import co.pacastrillonp.ualamobilechallenge.data.persistence.AppDatabase.Companion.V01
import co.pacastrillonp.ualamobilechallenge.data.persistence.daos.CityDao
import co.pacastrillonp.ualamobilechallenge.data.persistence.entities.CityEntity

@Database(entities = [CityEntity::class], version = V01)
abstract class AppDatabase  : RoomDatabase() {
    companion object {
        const val V01 = 1
    }
    abstract fun cityDao(): CityDao
}