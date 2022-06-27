package com.borisov.map.ui.map.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borisov.map.domain.AppState
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.*

/**
 * @author Borisov Andrey on 26.06.2022
 **/
abstract class BaseMapViewModel : ViewModel() {
    private val operationLiveDataMut = MutableLiveData<AppState>()

    protected val viewModelScopeCoroutine = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    abstract fun handleError(throwable: Throwable): Any

    fun getOperationLiveData() = operationLiveDataMut

    abstract fun saveMarker(position: Point): Job
    abstract fun getMarkers(): Job

    override fun onCleared() {
        super.onCleared()
        viewModelScopeCoroutine
            .coroutineContext
            .cancel()
    }
}