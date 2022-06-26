package com.borisov.map.domain.models

import com.yandex.mapkit.geometry.Point

/**
 * @author Borisov Andrey on 26.06.2022
 **/
data class NewMarkerResult(
    val result: Boolean,
    val position: Point
) : DataResult
