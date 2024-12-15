package co.pacastrillonp.ualamobilechallenge.data.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import co.pacastrillonp.ualamobilechallenge.common.entities.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Query(
        """
        SELECT * FROM ${CityEntity.TABLE_NAME}
        WHERE (:prefix IS NULL OR name LIKE :prefix || '%')
        ORDER BY name ASC
        LIMIT :limit OFFSET :offset
        """
    )
    fun searchCitiesByPrefixWithPagination(
        prefix: String? = null,
        limit: Int,
        offset: Int
    ): Flow<List<CityEntity>>

    @Query("SELECT CASE WHEN COUNT(*) = 0 THEN 1 ELSE 0 END FROM ${CityEntity.TABLE_NAME}")
    suspend fun isTableEmpty(): Boolean

    @Query("UPDATE ${CityEntity.TABLE_NAME} SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateCityById(id: Int, isFavorite: Boolean)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cities: List<CityEntity>)
}
