package community.flock.kotlinx.rgxgen.iterators.suppliers

interface Supplier<T> {

    fun get(): T
}