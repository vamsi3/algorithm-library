package com.vamsi3.algorithm

import com.vamsi3.algorithm.datastructure.ImplicitTreapV1
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class ImplicitTreapTest {
    @Nested
    inner class Common {
        @Test
        fun `insert and get`() {
            val implicitTreapV1 = ImplicitTreapV1<String>()
            implicitTreapV1.add("string1")
            implicitTreapV1.add("string6")
            implicitTreapV1.add("string2")
            implicitTreapV1.add("string9")
            implicitTreapV1.add("string4")
            implicitTreapV1.add(2, "string3")
            implicitTreapV1.removeAt(4)

            val implicitTreapV12 = ImplicitTreapV1<String>()
            implicitTreapV12.add("string1")
            implicitTreapV12.add("string2")
            implicitTreapV12.add("string3")
            implicitTreapV12.add("string2")
            implicitTreapV12.add("string4")

            println(implicitTreapV1 == implicitTreapV12)

        }
    }
}
