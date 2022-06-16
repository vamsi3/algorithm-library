package com.vamsi3.algorithm.geometry

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class Point2DTest {

    @Nested
    inner class CrossProduct {

        @Test
        fun `basic test - Int`() {
            val point1 = Point2D(1, 4)
            val point2 = Point2D(-2, 3)
            val value = point1.crossProduct(point2)
            assertEquals(11, value)
        }

        @Test
        fun `basic test - Long`() {
            val point1 = Point2D(1L, 4L)
            val point2 = Point2D(-2L, 3L)
            val value = point1.crossProduct(point2)
            assertEquals(11L, value)
        }

    }

}
