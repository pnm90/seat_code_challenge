package es.pnm.seatcode.core.domain.model

import es.pnm.seatcode.core.domain.model.enums.CompassPoint

class OrientedPosition(x: Int, y: Int, var orientation: CompassPoint) : Position(x, y) {
    override fun toString(): String {
        return "OrientedPosition(x=$x y=$y orientation=$orientation)"
    }
}

