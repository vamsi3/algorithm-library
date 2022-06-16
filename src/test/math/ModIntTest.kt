package com.vamsi3.algorithm.math

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class ModIntTest {

    @Nested
    inner class Addition {

        @Test
        fun `basic test - assignEquals`() {
            var a = ModInt(-3, 5)
            val b = ModInt(16, 5)
            a += b
            assertEquals(3, a.toInt())
        }

    }

}
