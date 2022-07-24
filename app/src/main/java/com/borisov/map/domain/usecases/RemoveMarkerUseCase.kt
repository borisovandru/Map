package com.borisov.map.domain.usecases

import com.borisov.map.domain.repository.CacheRepository

/**
 * @author Borisov Andrey on 26.06.2022
 **/
class RemoveMarkerUseCase(private val repository: CacheRepository) {
    suspend fun execute(markerId: Int) = repository.removeMarker(markerId = markerId)
}