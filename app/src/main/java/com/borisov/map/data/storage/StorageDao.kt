package com.borisov.map.data.storage

import androidx.room.*
import com.borisov.map.data.storage.entity.Marker

/**
 * @author Borisov Andrey on 26.06.2022
 **/
@Dao
interface StorageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMarker(marker: Marker)

    @Query("SELECT * FROM tab_map_markers ORDER by title")
    fun getMarkers(): List<Marker>

    @Query("DELETE FROM tab_map_markers WHERE markerId = :markerId")
    fun removeMarker(markerId: Int)

    @Update
    fun updateMarker(marker: Marker)
}