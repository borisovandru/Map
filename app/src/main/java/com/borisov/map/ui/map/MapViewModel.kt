package com.borisov.map.ui.map

import com.borisov.map.domain.AppState
import com.borisov.map.domain.models.MarkerDomain
import com.borisov.map.domain.usecases.AddMarkerUseCase
import com.borisov.map.domain.usecases.GetMarkersUseCase
import com.borisov.map.ui.map.base.BaseMapViewModel
import com.yandex.mapkit.geometry.Point
import kotlinx.coroutines.launch

/**
 * @author Borisov Andrey on 23.06.2022
 **/

class MapViewModel(
    private val addMarkerUseCase: AddMarkerUseCase,
    private val getMarkersUseCase: GetMarkersUseCase
) : BaseMapViewModel() {

    override fun handleError(throwable: Throwable) {
        getOperationLiveData().postValue(AppState.Error(throwable))
    }

    override fun saveMarker(position: Point) =
        viewModelScopeCoroutine.launch {
            getOperationLiveData()
                .postValue(
                    addMarkerUseCase.execute(
                        MarkerDomain(
                            latitude = position.latitude,
                            longitude = position.longitude
                        )
                    )
                )
        }

    override fun getMarkers() =
        viewModelScopeCoroutine.launch {
            getOperationLiveData()
                .postValue(getMarkersUseCase.execute())
        }
}