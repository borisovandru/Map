package com.borisov.map.ui.markers.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borisov.map.domain.AppState
import kotlinx.coroutines.*

/**
 * @author Borisov Andrey on 27.06.2022
 **/
abstract class BaseMarkersViewModel : ViewModel() {
    private val operationLiveData = MutableLiveData<AppState>()

    protected val viewModelScopeCoroutine = CoroutineScope(
        Dispatchers.IO
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        }
    )

    abstract fun handleError(throwable: Throwable): Any

    fun getOperationLiveData() = operationLiveData

    abstract fun getMarkers(): Job
    abstract fun removeMarker(markerId: Int): Job
}