package com.vamsi3.algorithm.math

enum class Algorithm {
    MODULAR_MULTIPLICATIVE_INVERSE,
}

/**
 * @return whole number solutions to a * x + b * y = c
 */
fun solve(a: Long, b: Long, c: Long, algorithm: Algorithm) = when (algorithm) {
    Algorithm.MODULAR_MULTIPLICATIVE_INVERSE -> solveWithModularMultiplicativeInverse(a, b, c)
}

fun solveWithModularMultiplicativeInverse(a: Long, b: Long, c: Long): Sequence<Pair<Long, Long>> {
    val gcd = gcd(a, b)
    if (c % gcd != 0L) return emptySequence()

    val aReduced = a / gcd
    val bReduced = b / gcd
    val cReduced = c / gcd

    val x = ModInt(aReduced, bReduced.toInt()).inverse * cReduced
    val y = (c - a * x) / b

    return generateSequence(x.toLong() to y.toLong()) { (x, y) -> x + aReduced to y - bReduced }
        .takeWhile { (x, y) -> x >= 0 && y >= 0 }
}
