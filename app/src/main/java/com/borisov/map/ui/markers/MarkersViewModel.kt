package com.borisov.map.ui.markers

import com.borisov.map.domain.AppState
import com.borisov.map.domain.usecases.GetMarkersUseCase
import com.borisov.map.domain.usecases.RemoveMarkerUseCase
import com.borisov.map.ui.markers.base.BaseMarkersViewModel
import kotlinx.coroutines.launch

/**
 * @author Borisov Andrey on 23.06.2022
 **/
class MarkersViewModel(
    private val getMarkersUseCase: GetMarkersUseCase,
    private val removeMarkerUseCase: RemoveMarkerUseCase,
) : BaseMarkersViewModel() {

    override fun getMarkers() =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)
            getOperationLiveData()
                .postValue(getMarkersUseCase.execute())
        }

    override fun removeMarker(markerId: Int) =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)

            getOperationLiveData()
                .postValue(removeMarkerUseCase.execute(markerId))

            getOperationLiveData()
                .postValue(getMarkersUseCase.execute())
        }

    override fun handleError(throwable: Throwable) {
        getOperationLiveData().postValue(AppState.Error(throwable))
    }
}