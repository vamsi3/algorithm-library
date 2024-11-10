package com.vamsi3.algorithm.math

fun primitiveRootModuloN(n: Long): Long? {
    return when {
        n <= 0 -> throw IllegalArgumentException()
        n <= 4 -> n - 1
        else -> {
            val ϕ = eulerTotientFunction(n.toInt())
            val validationExponents = primeDivisors(ϕ).map { ϕ / it }.toSet()
            return (2L..n)
                .filter { i -> gcd(i, n) == 1L }
                .find { i -> validationExponents.all { ModInt(i, n.toInt()).power(it).toInt() != 1 } }
        }
    }
}

