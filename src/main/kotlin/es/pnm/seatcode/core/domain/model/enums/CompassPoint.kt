package es.pnm.seatcode.core.domain.model.enums

enum class CompassPoint(val verboseName: String, val degrees: Int) {

    N("North", 90),
    W("West", 180),
    S("South", 270),
    E("East", 0);

    companion object {
        private val map = CompassPoint.values().associateBy { it.degrees }
        infix fun from(value: Int) = map[reduce360(value)]

        private fun reduce360(value: Int): Int {
            var degrees = value
            if (degrees >= 360) {
                degrees %= 360
            }
            if (degrees < 0) {
                degrees += 360
            }
            return degrees
        }
    }

}