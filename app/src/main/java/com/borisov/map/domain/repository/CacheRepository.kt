package com.borisov.map.domain.repository

import com.borisov.map.domain.models.MarkerDomain

/**
 * @author Borisov Andrey on 26.06.2022
 **/
interface CacheRepository {

    suspend fun addMarker(marker: MarkerDomain)

    suspend fun getMarkers(): List<MarkerDomain>

    suspend fun removeMarker(markerId: Int)

    suspend fun updateMarker(marker: MarkerDomain)
}