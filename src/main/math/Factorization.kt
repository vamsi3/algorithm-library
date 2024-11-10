package com.vamsi3.algorithm.math

fun primeDivisors(number: Int) = sequence {
    var numberMutable = number
    generateSequence(2) { it + 1 }
        .takeWhile { it * it <= numberMutable }
        .filter { numberMutable % it == 0 }
        .forEach {
            yield(it)
            while (numberMutable % it == 0) numberMutable /= it
        }
    if (numberMutable > 1) yield(numberMutable)
}
