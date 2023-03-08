package es.pnm.seatcode.core.domain.model

class Plateau(
    val dimension: Dimension,
    var mowers: List<Mower>
) {


    var grid: Grid = Grid(gridField = Array(dimension.endPosition.x + 1) {
        arrayOfNulls(dimension.endPosition.y + 1)
    })

    companion object {
        fun buildFromString(input: String): Plateau {
            val splittedInput = input.chunked(1)
            return Plateau(
                dimension = Dimension(
                    initPosition = Position(x = 0, y = 0),
                    endPosition = Position(
                        x = splittedInput[0].toInt(),
                        y = splittedInput[1].toInt()
                    )
                ),
                mowers = emptyList()
            )
        }


    }

    fun addMowers(input: List<Mower>) {
        mowers = input
    }


}