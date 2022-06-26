package com.borisov.map.domain.usecases

import com.borisov.map.domain.AppState
import com.borisov.map.domain.models.MarkerDomain
import com.borisov.map.domain.repository.CacheRepository

/**
 * @author Borisov Andrey on 26.06.2022
 **/
class AddMarkerUseCase(private val repository: CacheRepository) {
    suspend fun execute(marker: MarkerDomain): AppState =
        repository.addMarker(marker = marker)
}