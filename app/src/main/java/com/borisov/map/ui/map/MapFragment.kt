package com.borisov.map.ui.map

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.InputListener
import com.yandex.mapkit.map.Map
import com.yandex.runtime.image.ImageProvider
import com.borisov.map.R
import com.borisov.map.domain.AppState
import com.borisov.map.domain.models.MarkersResult
import com.borisov.map.domain.models.NewMarkerResult
import com.borisov.map.ui.Screen
import com.borisov.map.ui.map.base.BaseMapFragment
import com.borisov.map.utils.showSnakeBar

/**
 * @author Borisov Andrey on 23.06.2022
 **/

class MapFragment : BaseMapFragment(), InputListener, Screen {

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.mapView.map.addInputListener(this)
        viewModel.getMarkers()
        viewModel.getOperationLiveData()
            .observe(viewLifecycleOwner) { res -> renderData(result = res) }
    }

    private fun renderData(result: AppState) {
        when (result) {
            is AppState.Error -> {
                loading(true)
            }
            AppState.Loading -> {
                loading(true)
            }
            is AppState.Success -> {
                loading(true)
                renderSuccess(result)
            }
        }
    }

    private fun renderSuccess(result: AppState.Success) {
        when (val marker = result.data) {
            is NewMarkerResult -> {
                mapAddNewMarker(marker)
            }
            is MarkersResult -> {
                mapObjects?.clear()
                mapAddAllMarkers(marker)
            }
        }
    }

    /* Отрисовать все маркеры, имеющиеся в базе*/
    private fun mapAddAllMarkers(marker: MarkersResult) {
        marker.result.forEach {
            mapObjects?.addPlacemark(
                Point(it.latitude, it.longitude),
                ImageProvider.fromResource(
                    requireContext(),
                    R.drawable.ic_marker
                )
            )
        }
    }

    /* Отрисовать новый добавленный маркер*/
    private fun mapAddNewMarker(marker: NewMarkerResult) {
        mapObjects?.addPlacemark(
            Point(marker.position.latitude, marker.position.longitude),
            ImageProvider.fromResource(
                requireContext(),
                R.drawable.ic_marker
            )
        )
    }

    override fun onMapTap(map: Map, point: Point) {
        viewModel.saveMarker(point)
    }

    override fun onMapLongTap(map: Map, point: Point) {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding.mapView.map.removeInputListener(this)
    }

    override fun loading(isLoading: Boolean) {
        viewBinding.progress.isVisible = isLoading
    }

    override fun showError(throwable: Throwable) {
        throwable.localizedMessage?.let { viewBinding.root.showSnakeBar(it) }
    }
}
