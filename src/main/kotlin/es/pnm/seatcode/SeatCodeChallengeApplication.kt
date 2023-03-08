package es.pnm.seatcode

import es.pnm.seatcode.core.domain.model.Mower
import es.pnm.seatcode.core.domain.model.Plateau
import es.pnm.seatcode.core.domain.ports.service.CutGrass
import es.pnm.seatcode.core.domain.ports.service.driven.CutGrassManager
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class SeatCodeChallengeApplication


private lateinit var cutGrasService: CutGrass

fun main(args: Array<String>) {
    runApplication<SeatCodeChallengeApplication>(*args)

    cutGrasService = CutGrassManager()

    val plateauSeat = Plateau.buildFromString("55")
    val mower1 = Mower.buildFromString("12N")
    mower1.addMovementsFromString("LMLMLMLMM")
    val mower2 = Mower.buildFromString("33E")
    mower2.addMovementsFromString("MMRMMRMRRM")

    plateauSeat.addMowers(listOf(mower1, mower2))

    println("mower initial  x ${mower1.initialPosition.x} y ${mower1.initialPosition.y} ${mower1.initialPosition.orientation}")
    val result = cutGrasService.execute(plateauSeat)

    println("plateau data${plateauSeat.grid.gridField.contentDeepToString()}")

    println("Final Result : $result")

}
