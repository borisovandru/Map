package com.borisov.map.data.repository

import com.borisov.map.data.repository.datasource.CacheDataSource
import com.borisov.map.domain.models.MarkerDomain
import com.borisov.map.domain.repository.CacheRepository

/**
 * @author Borisov Andrey on 26.06.2022
 **/
class CacheRepositoryImpl(private val dataSource: CacheDataSource) : CacheRepository {
    override suspend fun addMarker(marker: MarkerDomain) =
        dataSource.addMarker(marker = marker)

    override suspend fun getMarkers(): List<MarkerDomain> =
        dataSource.getMarkers()

    override suspend fun removeMarker(markerId: Int) =
        dataSource.removeMarker(markerId = markerId)

    override suspend fun updateMarker(marker: MarkerDomain) =
        dataSource.updateMarker(marker = marker)
}