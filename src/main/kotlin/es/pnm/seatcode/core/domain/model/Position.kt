package es.pnm.seatcode.core.domain.model

open class Position(var x: Int, var y: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Position) return false

        if (x != other.x) return false
        if (y != other.y) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        return result
    }
}
