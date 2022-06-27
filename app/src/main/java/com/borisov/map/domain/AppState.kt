package com.borisov.map.domain

import com.borisov.map.domain.models.DataResult

/**
 * @author Borisov Andrey on 26.06.2022
 **/
sealed class AppState {
    data class Success(val data: DataResult) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}
