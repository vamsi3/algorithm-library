package com.vamsi3.algorithm.math

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class UnclassifiedTest {
    private val primitiveRootsData = mapOf(
        1 to setOf(0),
        2 to setOf(1),
        3 to setOf(2),
        4 to setOf(3),
        5 to setOf(2, 3),
        6 to setOf(5),
        7 to setOf(3, 5),
        9 to setOf(2, 5),
        10 to setOf(3, 7),
        11 to setOf(2, 6, 7, 8),
        13 to setOf(2, 6, 7, 11),
        14 to setOf(3, 5),
        17 to setOf(3, 5, 6, 7, 10, 11, 12, 14),
        18 to setOf(5, 11),
        19 to setOf(2, 3, 10, 13, 14, 15),
        22 to setOf(7, 13, 17, 19),
        23 to setOf(5, 7, 10, 11, 14, 15, 17, 19, 20, 21),
        25 to setOf(2, 3, 8, 12, 13, 17, 22, 23),
        26 to setOf(7, 11, 15, 19),
        27 to setOf(2, 5, 11, 14, 20, 23),
        29 to setOf(2, 3, 8, 10, 11, 14, 15, 18, 19, 21, 26, 27),
        31 to setOf(3, 11, 12, 13, 17, 21, 22, 24),
    )

    @Nested
    inner class Common {
//        @Test
//        fun `primitiveRootModuloN`() {
//            for (number in 1..31) {
//                val primitiveRoot = primitiveRootModuloN(number)
//                assertTrue { primitiveRootsData[number]?.contains(primitiveRoot) ?: (primitiveRoot == null) }
//            }
//        }
    }
}