package community.flock.kotlinx.rgxgen.data

import community.flock.kotlinx.rgxgen.nodes.Node

interface DataInterface {
    fun hasEstimatedCount(): Boolean

    fun hasAllUniqueValues(): Boolean

    fun useFindForMatching(): Boolean

    fun isUsableWithJavaPattern(): Boolean

    fun getPattern(): String?

    fun getResultNode(): Node?

    fun getEstimatedCount(): Long?

    fun getAllUniqueValues(): List<String?>?
}
