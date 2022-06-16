package com.vamsi3.algorithm.math

fun primeDivisors(number: Int) = sequence {
    var d = 2
    var n = number
    while (d * d <= n) {
        if (n % d != 0) continue
        while (n % d == 0) n /= d
        yield(d)
        ++d
    }
    if (n > 1) yield(n)
}
