package com.vamsi3.algorithm.geometry

class Point2D<T : Any>(val x: T, val y: T)

fun Point2D<Int>.crossProduct(other: Point2D<Int>) = x * other.y - y * other.x
fun Point2D<Long>.crossProduct(other: Point2D<Long>) = x * other.y - y * other.x
