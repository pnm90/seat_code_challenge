package es.pnm.seatcode.core.domain.ports.service

import es.pnm.seatcode.core.domain.model.OrientedPosition
import es.pnm.seatcode.core.domain.model.Plateau


interface CutGrass {
    fun execute(plateau: Plateau): List<OrientedPosition>
}