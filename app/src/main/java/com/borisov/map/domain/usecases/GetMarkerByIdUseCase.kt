package com.borisov.map.domain.usecases

import com.borisov.map.domain.AppState
import com.borisov.map.domain.repository.CacheRepository

/**
 * @author Borisov Andrey on 27.06.2022
 **/
class GetMarkerByIdUseCase(private val repository: CacheRepository) {
    suspend fun execute(markerId: Int): AppState =
        repository.getMarkerById(markerId = markerId)
}