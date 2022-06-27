package com.borisov.map.ui.markers.base

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.borisov.map.R
import com.borisov.map.databinding.FragmentMarkersBinding
import com.borisov.map.ui.markers.MarkersViewModel

/**
 * @author Borisov Andrey on 26.06.2022
 **/
abstract class BaseMarkersFragment : Fragment(R.layout.fragment_markers) {
    protected val viewModel: MarkersViewModel by viewModel()
    protected val viewBinding: FragmentMarkersBinding by viewBinding()
}