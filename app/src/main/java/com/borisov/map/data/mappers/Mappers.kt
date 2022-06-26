package com.borisov.map.data.mappers

import com.borisov.map.data.storage.entity.Marker
import com.borisov.map.domain.models.MarkerDomain

/**
 * @author Borisov Andrey on 26.06.2022
 **/
fun markerToDataLayer(target: MarkerDomain): Marker =
    Marker(
        markerId = target.markerId,
        latitude = target.latitude,
        longitude = target.longitude,
        visible = target.visible,
        title = target.title,
        description = target.description
    )