package com.borisov.map.ui.edit

import com.borisov.map.domain.AppState
import com.borisov.map.domain.models.MarkerDomain
import com.borisov.map.domain.usecases.GetMarkerByIdUseCase
import com.borisov.map.domain.usecases.UpdateMarkerUseCase
import com.borisov.map.ui.edit.base.BaseEditViewModel
import kotlinx.coroutines.launch

/**
 * @author Borisov Andrey on 27.06.2022
 **/
class EditViewModel(
    private val updateMarkerUseCase: UpdateMarkerUseCase,
    private val getMarkerByIdUseCase: GetMarkerByIdUseCase,
) : BaseEditViewModel() {

    override fun handleError(throwable: Throwable) =
        getOperationLiveData().postValue(AppState.Error(throwable))

    override fun updateMarker(marker: MarkerDomain) =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)
            getOperationLiveData().postValue(
                updateMarkerUseCase.execute(marker)
            )
        }

    override fun getMarker(markerId: Int) =
        viewModelScopeCoroutine.launch {
            getOperationLiveData().postValue(AppState.Loading)
            getOperationLiveData().postValue(getMarkerByIdUseCase.execute(markerId))
        }
}