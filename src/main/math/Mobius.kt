package com.vamsi3.algorithm.math

fun generateMobiusFunction(upperBound: Int): IntArray {
    val mobius = IntArray(upperBound) { 0 }
    mobius[1] = 1
    for (i in 1 until upperBound / 2) {
        for (j in 2 * i until upperBound step i) {
            mobius[j] -= mobius[i]
        }
    }
    return mobius
}
