package com.borisov.map.ui

/**
 * @author Borisov Andrey on 27.06.2022
 **/
class Publisher {
    private val observers: MutableList<UpdateObserver>

    fun subscribe(observer: UpdateObserver) {
        observers.add(observer)
    }

    fun unsubscribe(observer: UpdateObserver) {
        observers.remove(observer)
    }

    fun startUpdate() {
        for (observer in observers) {
            observer.updateMarkers()
        }
    }

    init {
        observers = ArrayList<UpdateObserver>()
    }
}