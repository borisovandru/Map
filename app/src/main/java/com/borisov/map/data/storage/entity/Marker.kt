package com.borisov.map.data.storage.entity

import androidx.room.Entity
import com.borisov.map.domain.models.MarkerDomain

/**
 * @author Borisov Andrey on 26.06.2022
 **/
@Entity(
    tableName = "tab_map_markers",
    primaryKeys = ["markerId"]
)
data class Marker(
    val markerId: Int,
    val latitude: Double,
    val longitude: Double,
    val visible: Boolean = true,
    val title: String,
    val description: String,
) {
    fun toDomain(): MarkerDomain =
        MarkerDomain(
            markerId = this.markerId,
            latitude = this.latitude,
            longitude = this.longitude,
            visible = this.visible,
            title = this.title,
            description = this.description
        )
}