package es.pnm.seatcode.core.domain.ports.service.driven

import es.pnm.seatcode.core.domain.model.Grid
import es.pnm.seatcode.core.domain.model.Mower
import es.pnm.seatcode.core.domain.model.OrientedPosition
import es.pnm.seatcode.core.domain.model.Plateau
import es.pnm.seatcode.core.domain.model.enums.CompassPoint
import es.pnm.seatcode.core.domain.model.enums.MovingDirection
import es.pnm.seatcode.core.domain.ports.service.CutGrass
import org.springframework.stereotype.Service

@Service
class CutGrassManager : CutGrass {

    override fun execute(plateau: Plateau): List<OrientedPosition> {
        val mowerEndPosition= ArrayList<OrientedPosition>()
        plateau.mowers.forEach {
            println("mower ${it.movements}")
            moveMower(plateau.grid, it)
            mowerEndPosition.add(it.currentPosition)
        }

        return mowerEndPosition

    }

    fun moveMower(grid: Grid, mower: Mower) {
        mower.movements.forEach {
            println("mower pos ${mower.currentPosition}")
            if (it == MovingDirection.M) {
                moveMowerForward(grid, mower)
            } else {
                spinOrientation(mower, it)
            }
        }
    }

    fun moveMowerForward(grid: Grid, mower: Mower) =
        mower.let {
            when (it.currentPosition.orientation) {
                CompassPoint.N -> it.currentPosition.y++
                CompassPoint.S -> it.currentPosition.y--
                CompassPoint.E -> it.currentPosition.x++
                CompassPoint.W -> it.currentPosition.x--
            }
        }

}

fun spinOrientation(mower: Mower, spinDirection: MovingDirection) = mower.let {
    val degrees = it.currentPosition.orientation.degrees + spinDirection.degrees
    it.currentPosition.orientation = CompassPoint.from(degrees)!!
}
