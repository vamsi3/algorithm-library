package com.vamsi3.algorithm.math

/*
 * Generate numbers in ascending order only from
 * the given set of primes as prime factors
 */
fun <T: Number> generateNumbersFromPrimes(primes: List<T>) = sequence {
    data class NextNumber(val prime: Long, var number: Long = prime, var indexToGeneratedNumbers: Int = 0)
    val potentialNextNumbers = primes.map { NextNumber(it.toLong()) }.toTypedArray()
    val generatedNumbers = mutableListOf<Long>(1)

    generateSequence(1, Long::inc).forEach { _ ->
        val nextNumber = potentialNextNumbers.minOf { it.number }
        yield(nextNumber)
        generatedNumbers.add(nextNumber)
        potentialNextNumbers.filter { it.number == nextNumber }.forEach { it.apply {
                ++indexToGeneratedNumbers
                number = prime * generatedNumbers[indexToGeneratedNumbers]
            }
        }
    }
}

/*
 * In number theory, an n-smooth (or n-friable) number is
 * an integer whose prime factors are all less than or equal to n.
 */
fun generateSmoothNumber(n: Int) = generateNumbersFromPrimes(sieve(n))