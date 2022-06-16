package com.vamsi3.algorithm.math

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

internal class MobiusTest {
    private val mobiusFunction = intArrayOf(
        0, 1, -1, -1, 0, -1, 1, -1, 0, 0, 1, -1, 0, -1, 1, 1, 0, -1, 0, -1,
        0, 1, 1, -1, 0, 0, 1, 0, 0, -1, -1, -1, 0, 1, 1, 1, 0, -1, 1, 1,
        0, -1, -1, -1, 0, 0, 1, -1, 0, 0, 0, 1, 0, -1, 0, 1, 0, 1, 1, -1,
        0, -1, 1, 0, 0, 1, -1, -1, 0, 1, -1, -1, 0, -1, 1, 0
    )

    @Nested
    inner class GenerateMobiusFunction {
        @Test
        fun `first 75 values`() {
            for (n in 1..75) {
                val generatedMobiusFunction =  generateMobiusFunction(n)
                assertContentEquals(mobiusFunction.take(n + 1).toIntArray(), generatedMobiusFunction)
            }
        }
    }
}
