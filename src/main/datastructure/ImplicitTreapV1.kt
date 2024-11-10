package com.vamsi3.algorithm.datastructure

import kotlin.random.Random
import kotlin.random.nextULong

class ImplicitTreapV1<E>: MutableList<E> {
    private class Node<E>(
        var value: E,
        left: Node<E>? = null,
        right: Node<E>? = null,
    ) {
        var left: Node<E>? = left
            set(value) {
                field = value
                applySizeInvariant()
            }

        var right: Node<E>? = right
            set(value) {
                field = value
                applySizeInvariant()
            }

        var size: Int = 0
            private set

        val priority: ULong = Random.nextULong()

        init {
            applySizeInvariant()
        }

        private fun applySizeInvariant() {
            size = 1 + (left?.size ?: 0) + (right?.size ?: 0)
        }

        fun asSequence(): Sequence<E> = sequence {
            left?.asSequence()?.let { yieldAll(it) }
            yield(value)
            left?.asSequence()?.let { yieldAll(it) }
        }
    }

    private var root: Node<E>? = null

    override val size: Int
        get() = root?.size ?: 0

    /**
     * @return pair of split trees such that first [key] elements are in first element of returned pair.
     */
    private fun Node<E>?.split(key: Int, parentImplicitKey: Int = 0): Pair<Node<E>?, Node<E>?> {
        val implicitKey = parentImplicitKey + (this?.left?.size ?: 0)

        return when {
            this == null -> null to null
            key <= implicitKey -> {
                val (left, right) = this.left.split(key, parentImplicitKey)
                this.left = right
                left to this
            }
            else -> {
                val (left, right) = this.right.split(key, 1 + implicitKey)
                this.right = left
                this to right
            }
        }
    }

    private fun Node<E>?.erase(key: Int, parentImplicitKey: Int = 0): Pair<Node<E>?, E?> {
        val implicitKey = parentImplicitKey + (this?.left?.size ?: 0)

        return when {
            this == null -> null to null
            key == implicitKey -> left.merge(right) to value
            key < implicitKey -> {
                val (newLeft, removedElement) = left.erase(key, parentImplicitKey)
                left = newLeft
                this to removedElement
            }
            else -> {
                val (newRight, removedElement) = right.erase(key, 1 + implicitKey)
                right = newRight
                this to removedElement
            }
        }
    }

    private fun Node<E>?.merge(other: Node<E>?): Node<E>? {
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




    override fun iterator(): MutableIterator<E> = IteratorImpl()

    private open inner class IteratorImpl : MutableIterator<E> {
        protected var index = 0
        protected var nodeSequence = root?.asSequence()?.iterator()

        override fun hasNext() = nodeSequence?.hasNext() ?: false

        override fun next(): E {
            if (!hasNext()) throw NoSuchElementException()
            index += 1
            return nodeSequence!!.next()
        }

        override fun remove() {
            if (index == 0) throw IllegalStateException()
            removeAt(index - 1)
        }
    }

    override fun listIterator(): MutableListIterator<E> = ListIteratorImpl()
    override fun listIterator(index: Int): MutableListIterator<E> = ListIteratorImpl(index)

    private inner class ListIteratorImpl(index: Int = 0): IteratorImpl(), MutableListIterator<E> {
        init {
            if (index < 0 || index > size) {
                throw IndexOutOfBoundsException()
            }
            this.index = index
        }

        override fun nextIndex(): Int = index
        override fun hasPrevious(): Boolean = index > 0

        override fun previous(): E {
            if (!hasPrevious()) throw NoSuchElementException()
            return get(--index)
        }

        override fun previousIndex(): Int = index - 1

        override fun add(element: E) {
            add(index++, element)
        }

        override fun set(element: E) {
            set(index - 1, element)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is ImplicitTreapV1<*>) return false
        if (size != other.size) return false
        return asSequence().zip(other.asSequence()).all { (elem, elemOther) -> elem == elemOther }
    }

    override fun hashCode() = fold(1) { hashCode, e -> 31 * hashCode + (e?.hashCode() ?: 0) }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<E> {
        TODO("Not yet implemented")
    }

    override fun add(element: E): Boolean {
        root = root.merge(Node(element))
        return true
    }

    override fun add(index: Int, element: E) {
        val (left, right) = root.split(index)
        root = left.merge(Node(element)).merge(right)
    }

    override fun addAll(elements: Collection<E>): Boolean {
        if (elements.isEmpty()) return false
        elements.forEach { root = root.merge(Node(it)) }
        return true
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        if (elements.isEmpty()) return false
        var (left, right) = root.split(index)
        elements.forEach { left = left.merge(Node(it)) }
        root = left.merge(right)
        return true
    }

    override fun clear() {
        root = null
    }

    override fun get(index: Int): E {
        val (leftWithIndexNode, right) = root.split(index + 1)
        val (left, indexNode) = leftWithIndexNode.split(index)
        val value = indexNode?.value
        root = left.merge(indexNode).merge(right)
        return value ?: throw IndexOutOfBoundsException()
    }

    override fun set(index: Int, element: E): E {
        val (leftWithIndexNode, right) = root.split(index + 1)
        val (left, indexNode) = leftWithIndexNode.split(index)
        val previousElement = indexNode?.value
        indexNode?.value = element
        root = left.merge(indexNode).merge(right)
        return previousElement ?: throw IndexOutOfBoundsException()
    }

    override fun removeAt(index: Int): E {
        val (newRoot, removedElement) = root.erase(index)
        root = newRoot
        return removedElement ?: throw IndexOutOfBoundsException()
    }

    override fun remove(element: E): Boolean {
        val index = indexOf(element)
        if (index == -1) return false
        removeAt(index)
        return true
    }

    override fun isEmpty() = root == null

    override fun contains(element: E) = any { it == element }
    override fun containsAll(elements: Collection<E>) = elements.all { contains(it) }

    override fun indexOf(element: E) = indexOfFirst { it == element }
    override fun lastIndexOf(element: E) = indexOfLast { it == element }

    override fun removeAll(elements: Collection<E>) = elements.any { remove(it) }
    override fun retainAll(elements: Collection<E>) = retainAll { elements.contains(it) }
}
