package co.pacastrillonp.ualamobilechallenge.data.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.pacastrillonp.ualamobilechallenge.data.persistence.entities.CityEntity

@Dao
interface CityDao {

    @Query("SELECT * FROM ${CityEntity.TABLE_NAME} WHERE name LIKE :prefix || '%' ORDER BY name ASC")
    fun searchCitiesByPrefix(prefix: String): List<CityEntity>

    @Query("SELECT * FROM ${CityEntity.TABLE_NAME} WHERE isFavorite = 1 ORDER BY name ASC")
    fun getFavoriteCities(): List<CityEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cities: List<CityEntity>)
}
