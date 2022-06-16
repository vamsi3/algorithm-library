package com.vamsi3.algorithm.math

class ModInt private constructor(
    value: Long = 0,
    private val mod: Int,
) : Number() {
    private var value: Int = 0

    init {
        this.value = (if (value !in -mod + 1..mod) value % mod else value).toInt()
        if (this.value < 0) this.value += mod
    }

    constructor(value: Number, mod: Int) : this(value.toLong(), mod)

    override fun toInt(): Int = value
    override fun toByte(): Byte = value.toByte()
    override fun toChar(): Char = value.toChar()
    override fun toShort(): Short = value.toShort()
    override fun toLong(): Long = value.toLong()
    override fun toFloat(): Float = value.toFloat()
    override fun toDouble(): Double = value.toDouble()

    operator fun unaryPlus() = this
    operator fun unaryMinus() = ModInt(-value, mod)
    operator fun inc() = this + 1
    operator fun dec() = this - 1
    operator fun plus(other: Number): ModInt = ModInt(value + other.toLong(), mod)
    operator fun minus(other: Number): ModInt = ModInt(value - other.toLong(), mod)
    operator fun times(other: Number): ModInt = ModInt(value * other.toLong(), mod)
    operator fun div(other: Number): ModInt = this * ModInt(other, mod).inverse

    val inverse: ModInt
        get() {
            var a1 = mod
            var a2 = value
            var v1 = 0
            var v2 = 1
            while (a2 != 0) {
                val q = a1 / a2
                a1 -= q * a2
                a1 = a2.also { a2 = a1 }
                v1 -= q * v2
                v1 = v2.also { v2 = v1 }
            }
            return ModInt(v1, mod)
        }

    fun power(exponent: Int): ModInt = power(exponent.toLong())

    fun power(exponent: Long): ModInt {
        var a = copy()
        var r = ModInt(1, mod)
        var b: Int = (exponent % (mod - 1)).toInt()
        while (b > 0) {
            if (b % 2 == 1) r *= a
            a *= a
            b /= 2
        }
        return r
    }

    private fun copy(value: Number = this.value, mod: Int = this.mod) = ModInt(value, mod)
}

fun Number.toModInt(mod: Int) = ModInt(this, mod)
operator fun Number.plus(other: ModInt) = other + this
operator fun Number.minus(other: ModInt) = other - this
operator fun Number.times(other: ModInt) = other * this
operator fun Number.div(other: ModInt) = other / this
