package de.wulkanat.files.concept

import kotlinx.serialization.*

fun <T> autoSerializableMutableListOf(vararg elements: T): AutoSerializableMutableList<T> =
    AutoSerializableMutableList(mutableListOf(*elements))


@Serializable
class AutoSerializableMutableList<T>(
    private val instance: MutableList<T>
) : MutableList<T>, AutoSaveSerializable {
    @Transient
    override var parent: AutoSaveSerializable? = null

    override fun propagateParent() {
        for (item in instance) {
            if (item is AutoSaveSerializable) {
                item.parent = this
                item.propagateParent()
            }
        }
    }

    override fun add(element: T): Boolean {
        if (instance.add(element)) {
            save()
            return true
        }

        return false
    }

    override fun add(index: Int, element: T) {
        instance.add(index, element)
        save()
    }

    override operator fun get(index: Int): T {
        return instance[index]
    }

    override val size: Int
        get() = instance.size

    override fun contains(element: T): Boolean {
        return instance.contains(element)
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        return instance.containsAll(elements)
    }

    override fun indexOf(element: T): Int {
        return instance.indexOf(element)
    }

    override fun isEmpty(): Boolean {
        return instance.isEmpty()
    }

    override fun iterator(): MutableIterator<T> {
        return instance.iterator()
    }

    override fun lastIndexOf(element: T): Int {
        return instance.lastIndexOf(element)
    }

    override fun addAll(index: Int, elements: Collection<T>): Boolean {
        if (instance.addAll(index, elements)) {
            save()
            return true
        }

        return false
    }

    override fun addAll(elements: Collection<T>): Boolean {
        if (instance.addAll(elements)) {
            save()
            return true
        }

        return false
    }

    override fun clear() {
        instance.clear()
        save()
    }

    override fun listIterator(): MutableListIterator<T> {
        return instance.listIterator()
    }

    override fun listIterator(index: Int): MutableListIterator<T> {
        return instance.listIterator()
    }

    override fun remove(element: T): Boolean {
        if (instance.remove(element)) {
            save()
            return true
        }

        return false
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        if (instance.removeAll(elements)) {
            save()
            return true
        }

        return false
    }

    override fun removeAt(index: Int): T {
        val out = instance.removeAt(index)
        save()
        return out
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        if (instance.retainAll(elements)) {
            save()
            return true
        }

        return false
    }

    override fun set(index: Int, element: T): T {
        val out = instance.set(index, element)
        save()
        return out
    }

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<T> {
        return instance.subList(fromIndex, toIndex)
    }
}