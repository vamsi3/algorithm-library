package com.vamsi3.algorithm.math

/**
 * @see https://en.wikipedia.org/wiki/Euclidean_algorithm
 * @return gcd of a, b
 */
tailrec fun gcd(a: Long, b: Long): Long {
    return when {
        a == 0L -> b
        b == 0L -> a
        else -> gcd(b, a % b)
    }
}

enum class GcdCoefficientsAlgorithm {
    EXTENDED_EUCLIDEAN_RECURSIVE,
    EXTENDED_EUCLIDEAN_ITERATIVE,
}

/**
 * @see https://en.wikipedia.org/wiki/Extended_Euclidean_algorithm
 * @return x, y such that gcd of a * x + b * y = gcd(a, b)
 */
fun gcdCoefficients(
    a: Long,
    b: Long,
    algorithm: GcdCoefficientsAlgorithm = GcdCoefficientsAlgorithm.EXTENDED_EUCLIDEAN_ITERATIVE
) = when (algorithm) {
    GcdCoefficientsAlgorithm.EXTENDED_EUCLIDEAN_RECURSIVE -> gcdCoefficientsRecursive(a, b)
    GcdCoefficientsAlgorithm.EXTENDED_EUCLIDEAN_ITERATIVE -> gcdCoefficientsIterative(a, b)
}

private fun gcdCoefficientsRecursive(a: Long, b: Long): Pair<Long, Long> {
    return when {
        a == 0L -> 0L to 1L
        b == 0L -> 1L to 0L
        else -> gcdCoefficientsRecursive(b, a % b)
    }
}

private fun gcdCoefficientsIterative(a: Long, b: Long): Pair<Long, Long> {
    var a1 = a
    var a2 = b
    var u1: Long = 1
    var u2: Long = 0
    var v1: Long = 0
    var v2: Long = 1
    while (a2 != 0L) {
        val q: Long = a1 / a2
        a1 = a2.also { a2 = a1 - q * a2 }
        u1 = u2.also { u2 = u1 - q * u2 }
        v1 = v2.also { v2 = v1 - q * v2 }
    }
    return u1 to v1
}
