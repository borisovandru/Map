package com.borisov.map.data.repository.datasource

import com.borisov.map.data.mappers.markerToDataLayer
import com.borisov.map.data.storage.Storage
import com.borisov.map.domain.models.MarkerDomain

/**
 * @author Borisov Andrey on 26.06.2022
 **/
class CacheDataSourceImpl(private val storage: Storage) : CacheDataSource {
    override suspend fun addMarker(marker: MarkerDomain) =
        storage
            .storageDao()
            .addMarker(markerToDataLayer(marker))

    override suspend fun getMarkers(): List<MarkerDomain> =
        storage
            .storageDao()
            .getMarkers()
            .map { it.toDomain() }

    override suspend fun removeMarker(markerId: Int) =
        storage
            .storageDao()
            .removeMarker(markerId)

    override suspend fun updateMarker(marker: MarkerDomain) =
        storage
            .storageDao()
            .updateMarker(markerToDataLayer(marker))
}