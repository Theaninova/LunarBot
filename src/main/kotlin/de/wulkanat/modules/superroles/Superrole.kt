package de.wulkanat.modules.superroles

import de.wulkanat.files.concept.AutoSaveSerializable
import kotlinx.serialization.Serializable

@Serializable
class Superrole(
    val id: Long,
    val name: String,
    val prefix: String,
) : AutoSaveSerializable {
    override var parent: AutoSaveSerializable? = null

    override fun propagateParent() {
        TODO("Not yet implemented")
    }
}