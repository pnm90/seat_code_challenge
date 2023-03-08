package es.pnm.seatcode.core.domain.model

import es.pnm.seatcode.core.domain.model.enums.CompassPoint
import es.pnm.seatcode.core.domain.model.enums.MovingDirection

data class Mower(
    val initialPosition: OrientedPosition,
    var movements: ArrayList<MovingDirection> = ArrayList()
) {

    var currentPosition: OrientedPosition =
        OrientedPosition(initialPosition.x, initialPosition.y, initialPosition.orientation)


    companion object{
        fun buildFromString(input: String): Mower {
            val splitInput = input.chunked(1)
            return Mower(initialPosition = OrientedPosition(x = splitInput[0].toInt(),y=splitInput[1].toInt(), orientation = CompassPoint.valueOf(splitInput[2])))
        }
    }

    fun addMovementsFromString(input: String){
        val splitInput = input.chunked(1)
        splitInput.forEach { item -> movements.add(MovingDirection.valueOf(item)) }
    }
}
