package com.borisov.map.ui.markers.adaper

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.borisov.map.databinding.MarkerItemBinding
import com.borisov.map.domain.models.MarkerDomain
import com.borisov.map.utils.click
import com.borisov.map.utils.longClick

/**
 * @author Borisov Andrey on 27.06.2022
 **/
class MarkerViewHolder(
    view: View,
) : RecyclerView.ViewHolder(view) {

    private val viewBinding: MarkerItemBinding by viewBinding()

    fun bind(marker: MarkerDomain, delegate: MarkerAdapter.Delegate?) {
        with(viewBinding) {
            coordinate.text = marker.coordinateToString()
            title.text = marker.title
            description.text = marker.description
            root.click { delegate?.onItemClick(marker) }
            root.longClick {
                delegate?.onItemLongClick(marker)
                true
            }
        }
    }
}