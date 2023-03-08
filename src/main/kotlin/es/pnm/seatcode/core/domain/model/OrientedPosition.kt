package es.pnm.seatcode.core.domain.model

import es.pnm.seatcode.core.domain.model.enums.CompassPoint

class OrientedPosition(x: Int, y: Int, var orientation: CompassPoint) : Position(x, y) {

    companion object {
        fun buildFromString(input: String): OrientedPosition {
            val splitInput = input.chunked(1)
            return OrientedPosition(
                x = splitInput[0].toInt(),
                y = splitInput[1].toInt(),
                orientation = CompassPoint.valueOf(splitInput[2])
            )
        }
    }

    override fun toString(): String {
        return "OrientedPosition(x=$x y=$y orientation=$orientation)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is OrientedPosition) return false
        if (!super.equals(other)) return false

        if (orientation != other.orientation) return false

        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + orientation.hashCode()
        return result
    }


}

