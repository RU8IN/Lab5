package driver

import exceptions.XCoordinateException

data class Coordinates(private val x: Int?, private val y: Int?) {

    init {
        if ((x ?: 0) < -606) {
            throw XCoordinateException()
        }
    }
}