package com.vamsi3.algorithm.math

fun sieve(upperBound: Int): List<Int> {
    val isPrime = BooleanArray(upperBound + 1) { true }
    for (i in 2..upperBound) {
        if (!isPrime[i]) continue
        if (i.toLong() * i > upperBound) break
        for (j in i * i..upperBound step i) {
            isPrime[j] = false
        }
    }
    return isPrime.withIndex().filter { it.value && it.index >= 2 }.map { it.index }
}
