package com.vamsi3.algorithm.datastructure

import kotlin.random.Random
import kotlin.random.nextUInt

class Treap {
    class Node(
        val key: Int,
        val priority: UInt,
        var left: Node? = null,
        var right: Node? = null,
    ) {
        val count: UInt
            get() = (left?.count ?: 0u) + (right?.count ?: 0u)
    }

    private fun Node?.split(key: Int): Pair<Node?, Node?> {
        return when {
            this == null -> null to null
            key < this.key -> {
                val (left, right) = this.left.split(key)
                this.left = right
                left to this
            }
            else -> {
                val (left, right) = this.right.split(key)
                this.right = left
                this to right
            }
        }
    }

    private fun Node?.insert(key: Int, priority: UInt): Node? {
        return when {
            this == null -> null
            priority > this.priority -> {
                val (left, right) = this.split(key)
                Node(key, priority, left, right)
            }
            key < this.key -> {
                this.left = this.left.insert(key, priority)
                this
            }
            else -> {
                this.right = this.right.insert(key, priority)
                this
            }
        }
    }

    private fun Node?.merge(other: Node?): Node? {
        return when {
            this == null -> other
            other == null -> this
            priority > other.priority -> {
                right = right.merge(other)
                this
            }
            else -> {
                other.left = merge(other.left)
                other
            }
        }
    }

    private fun Node?.erase(key: Int): Node? {
        return when {
            this == null -> null
            key == this.key -> left.merge(right)
            key < this.key -> {
                left = left.erase(key)
                this
            }
            else -> {
                right = right.erase(key)
                this
            }
        }
    }

    private var root: Node? = null

    fun insert(key: Int, priority: UInt = Random.nextUInt()) {
        root = root.insert(key, priority)
    }

    fun erase(key: Int) {
        root = root.erase(key)
    }
}

