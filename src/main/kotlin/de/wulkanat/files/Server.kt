package de.wulkanat.files

import de.wulkanat.files.concept.AutoSaveSerializable
import kotlinx.serialization.Serializable

@Serializable
data class Server(
    val id: Long,
    @Transient override var parent: AutoSaveSerializable? = null
) : AutoSaveSerializable {
    override fun propagateParent() {
        // noop
    }
}