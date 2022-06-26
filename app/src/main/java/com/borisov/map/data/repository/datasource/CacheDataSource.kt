package com.borisov.map.data.repository.datasource

import com.borisov.map.domain.models.MarkerDomain

/**
 * @author Borisov Andrey on 26.06.2022
 **/
interface CacheDataSource {

    suspend fun addMarker(marker: MarkerDomain)

    suspend fun getMarkers(): List<MarkerDomain>

    suspend fun removeMarker(markerId: Int)

    suspend fun updateMarker(marker: MarkerDomain)
}