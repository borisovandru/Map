package com.borisov.map.data.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.borisov.map.data.storage.entity.Marker

/**
 * @author Borisov Andrey on 26.06.2022
 **/
@Database(
    exportSchema = true,
    entities = [Marker::class],
    version = 1
)
abstract class Storage : RoomDatabase() {
    abstract fun storageDao(): StorageDao
}