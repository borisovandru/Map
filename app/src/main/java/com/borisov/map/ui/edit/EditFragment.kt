package com.borisov.map.ui.edit

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.borisov.map.databinding.FragmentEditBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.borisov.map.domain.AppState
import com.borisov.map.domain.models.MarkerDomain
import com.borisov.map.domain.models.MarkerResult
import com.borisov.map.domain.models.OperationResult
import com.borisov.map.ui.Publisher
import com.borisov.map.ui.Updater
import com.borisov.map.ui.markers.MarkersFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 * @author Borisov Andrey on 27.06.2022
 **/
class EditFragment : BottomSheetDialogFragment() {

    private val viewBinding: FragmentEditBinding by viewBinding(CreateMethod.INFLATE)
    private val viewModel: EditViewModel by viewModel()
    private var publisher: Publisher? = null
    private val markerId: Int? by lazy { arguments?.getInt(MarkersFragment.KEY_MARKER_ID) }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        publisher = (requireContext() as Updater).getUpdater()
    }

    override fun onDetach() {
        super.onDetach()
        publisher = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.parseColor(TRANSPARENT_COLOR)))
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        markerId?.let {
            viewModel.getMarker(it)
        }

        viewModel.getOperationLiveData()
            .observe(viewLifecycleOwner) { res -> renderData(result = res) }

        initButtonClickListeners()
    }

    private fun initButtonClickListeners() {
        viewBinding.btnSave.setOnClickListener {
            markerId?.let {
                updateMarker(it)
            }
        }

        viewBinding.btnCancel.setOnClickListener {
            this.dismiss()
        }
    }

    private fun updateMarker(it: Int) {
        viewModel.updateMarker(
            MarkerDomain(
                markerId = it,
                latitude = viewBinding.lanValue.text.toString().toDouble(),
                longitude = viewBinding.lonValue.text.toString().toDouble(),
                visible = true,
                title = viewBinding.titleValue.text.toString(),
                description = viewBinding.descriptionValue.text.toString()
            )
        )
    }

    private fun renderData(result: AppState) {
        when (result) {
            is AppState.Error -> {}
            is AppState.Loading -> {}
            is AppState.Success -> renderSuccess(result)
        }
    }

    private fun renderSuccess(result: AppState.Success) {
        when (val marker = result.data) {
            is MarkerResult -> {
                renderData(marker)
            }
            is OperationResult -> {
                publisher?.startUpdate()
                this.dismiss()
            }
        }
    }

    private fun renderData(marker: MarkerResult) {
        with(viewBinding) {
            titleValue.setText(marker.result.title)
            descriptionValue.setText(marker.result.description)
            lonValue.setText(marker.result.longitude.toString())
            lanValue.setText(marker.result.latitude.toString())
        }
    }

    companion object {
        const val TAG = "EditFragmentBottomSheet"
        const val TRANSPARENT_COLOR = "#00000000"
        fun newInstance() = EditFragment()
    }
}