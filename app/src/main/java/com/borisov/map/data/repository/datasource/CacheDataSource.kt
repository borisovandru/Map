package com.borisov.map.data.repository.datasource

import com.borisov.map.domain.AppState
import com.borisov.map.domain.models.MarkerDomain

/**
 * @author Borisov Andrey on 26.06.2022
 **/
interface CacheDataSource {
    suspend fun addMarker(marker: MarkerDomain): AppState
    suspend fun getMarkers(): AppState
    suspend fun getMarkerById(markerId: Int): AppState
    suspend fun removeMarker(markerId: Int): AppState
    suspend fun updateMarker(marker: MarkerDomain): AppState
}