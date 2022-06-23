package com.borisov.map.ui.map

/**
 * @author Borisov Andrey on 23.06.2022
 **/
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.borisov.map.BuildConfig
import com.borisov.map.R
import com.borisov.map.databinding.FragmentMapBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition

abstract class BaseMapFragment : Fragment(R.layout.fragment_map) {
    private val viewBinding: FragmentMapBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        MapKitFactory.setApiKey(BuildConfig.YANDEX_KEY)
        MapKitFactory.initialize(requireContext())
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.mapView.map.isRotateGesturesEnabled = false
        viewBinding.mapView.map.move(
            CameraPosition(Point(DEF_LATITUDE, DEF_LONGITUDE), DEF_ZOOM, ZERO_FLOAT, ZERO_FLOAT),
            Animation(Animation.Type.SMOOTH, ZERO_FLOAT),
            null
        )
    }

    override fun onStart() {
        super.onStart()
        viewBinding.mapView.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        super.onStop()
        viewBinding.mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }

    companion object {
        const val DEF_LATITUDE = 54.390697
        const val DEF_LONGITUDE = 48.589128
        const val DEF_ZOOM = 16.0f
        const val ZERO_FLOAT = 0f
    }
}