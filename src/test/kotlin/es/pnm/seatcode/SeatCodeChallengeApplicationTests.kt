package es.pnm.seatcode

import es.pnm.seatcode.core.domain.model.Mower
import es.pnm.seatcode.core.domain.model.OrientedPosition
import es.pnm.seatcode.core.domain.model.Plateau
import es.pnm.seatcode.core.domain.model.enums.CompassPoint
import es.pnm.seatcode.core.domain.ports.service.CutGrass
import es.pnm.seatcode.core.domain.ports.service.driven.CutGrassManager
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource

class SeatCodeChallengeApplicationTests {

    private lateinit var sut: CutGrass

    @BeforeEach
    fun setUp() {
        sut = CutGrassManager()
    }

    @Test
    fun contextLoads() {
        assertTrue(true)
    }

    @Test
    fun `Given Gid dimension, mower1 initial position and list of orders, should return final mower1 position`() {
        //given
        val plateauSeat = Plateau.buildFromString("55")
        val mower1 = Mower.buildFromString("12N")
        mower1.addMovementsFromString("LMLMLMLMM")
        plateauSeat.addMowers(listOf(mower1))
        val expectedResult: List<OrientedPosition> =
            arrayListOf(OrientedPosition(x = 1, y = 3, orientation = CompassPoint.N))
        //when
        val result = sut.execute(plateauSeat)
        //then
        assertThat(expectedResult).containsExactlyInAnyOrderElementsOf(result)
    }


    @Test
    fun `Given Gid dimension, mower2 initial position and list of orders, should return final mower2 position`() {
        //given
        val plateauSeat = Plateau.buildFromString("55")
        val mower1 = Mower.buildFromString("33E")
        mower1.addMovementsFromString("MMRMMRMRRM")
        plateauSeat.addMowers(listOf(mower1))
        val expectedResult: List<OrientedPosition> =
            arrayListOf(OrientedPosition(x = 5, y = 1, orientation = CompassPoint.E))
        //when
        val result = sut.execute(plateauSeat)
        //then
        assertThat(expectedResult).containsExactlyInAnyOrderElementsOf(result)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["/inputValues.csv"])
//	@ValueSource(strings = ["Bob", "racecar", "Malayalam"])
    fun `Should pas tests`(
        plateau: String,
        mower1Position: String,
        mower1Orders: String,
        mower2Position: String,
        mower2Orders: String,
        mower1Result: String,
        mower2Result: String
    ) {
        //given
        val plateauSeat = Plateau.buildFromString(plateau)
        val mower1 = Mower.buildFromString(mower1Position)
        mower1.addMovementsFromString(mower1Orders)
        val mower2 = Mower.buildFromString(mower2Position)
        mower1.addMovementsFromString(mower2Orders)
        plateauSeat.addMowers(listOf(mower1, mower2))
        val expectedResult: List<OrientedPosition> =
            arrayListOf(
                OrientedPosition.buildFromString(mower1Result),
                OrientedPosition.buildFromString(mower2Result)
            )
        //when
        val result = sut.execute(plateauSeat)
        //then
        assertThat(expectedResult).containsExactlyInAnyOrderElementsOf(result)
    }

}
