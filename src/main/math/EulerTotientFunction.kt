package com.vamsi3.algorithm.math

fun eulerTotientFunction(n: Int): Int {
    var phi = n
    primeDivisors(n).forEach { primeDivisor -> phi -= phi / primeDivisor }
    return phi
}

fun generateEulerTotientFunction(n: Int, algorithm: GenerateEulerTotientFunctionAlgorithm) = when (algorithm) {
    GenerateEulerTotientFunctionAlgorithm.SIEVE_OF_ERATOSTHENES -> generateEulerTotientFunctionUsingSieveOfEratosthenes(n)
    GenerateEulerTotientFunctionAlgorithm.GAUSS_DIVISOR_SUM_PROPERTY -> generateEulerTotientFunctionUsingDivisorSumProperty(n)
}

enum class GenerateEulerTotientFunctionAlgorithm {
    SIEVE_OF_ERATOSTHENES,
    GAUSS_DIVISOR_SUM_PROPERTY,
}

fun generateEulerTotientFunctionUsingSieveOfEratosthenes(n: Int): IntArray {
    val phi = IntArray(n + 1) { if (it <= 1) it else it - 1 }
    for (i in 2..n / 2) {
        if (phi[i] != i - 1) continue
        for (j in 2 * i..n step i) {
            phi[j] -= phi[j] / i
        }
    }
    return phi
}

fun generateEulerTotientFunctionUsingDivisorSumProperty(n: Int): IntArray {
    val phi = IntArray(n + 1) { it }
    for (i in 2..n / 2) {
        for (j in i..n step i) {
            phi[j] -= phi[i]
        }
    }
    return phi
}
