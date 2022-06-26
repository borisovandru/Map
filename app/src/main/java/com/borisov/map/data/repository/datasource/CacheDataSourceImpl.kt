package com.borisov.map.data.repository.datasource

import com.borisov.map.data.mappers.markerToDataLayer
import com.borisov.map.data.storage.Storage
import com.borisov.map.domain.AppState
import com.borisov.map.domain.models.MarkerDomain
import com.borisov.map.domain.models.MarkersResult
import com.borisov.map.domain.models.NewMarkerResult
import com.borisov.map.domain.models.OperationResult
import com.yandex.mapkit.geometry.Point

/**
 * @author Borisov Andrey on 26.06.2022
 **/
class CacheDataSourceImpl(private val storage: Storage) : CacheDataSource {
    override suspend fun addMarker(marker: MarkerDomain) =
        try {
            val result = storage
                .storageDao()
                .addMarker(markerToDataLayer(marker))
            AppState.Success(
                NewMarkerResult(
                    result > 0, Point(
                        marker.latitude, marker.longitude
                    )
                )
            )
        } catch (err: Exception) {
            AppState.Error(err)
        }

    override suspend fun getMarkers() =
        try {
            val result = storage
                .storageDao()
                .getMarkers()
                .map { it.toDomain() }
            AppState.Success(MarkersResult(result))
        } catch (err: Exception) {
            AppState.Error(err)
        }

    override suspend fun removeMarker(markerId: Int) =
        try {
            val result = storage
                .storageDao()
                .removeMarker(markerId)
            AppState.Success(OperationResult(result > 0))
        } catch (err: Exception) {
            AppState.Error(err)
        }

    override suspend fun updateMarker(marker: MarkerDomain) =
        try {
            val result = storage
                .storageDao()
                .updateMarker(markerToDataLayer(marker))
            AppState.Success(OperationResult(result > 0))
        } catch (err: Exception) {
            AppState.Error(err)
        }
}