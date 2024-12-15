package co.pacastrillonp.ualamobilechallenge.common.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CityEntity.TABLE_NAME)
data class CityEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val country: String,
    val longitude: Double,
    val latitude: Double,
    val isFavorite: Boolean = false
){
    companion object {
        const val TABLE_NAME = "city_entity_table"
    }
}
