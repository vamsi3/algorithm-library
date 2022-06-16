package com.vamsi3.algorithm.math

fun generateMobiusFunction(upperBound: Int): IntArray {
    val mobius = IntArray(upperBound + 1) { 0 }
    mobius[1] = 1
    for (i in 1..upperBound / 2) {
        for (j in 2 * i..upperBound step i) {
            mobius[j] -= mobius[i]
        }
    }
    return mobius
}
