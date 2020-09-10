package de.wulkanat.extensions

infix fun String.encloses(other: String): Boolean {
    return this.first() == other.first() && this.last() == other.last()
}