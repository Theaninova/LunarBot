package de.wulkanat.modules.superroles

import de.wulkanat.files.concept.AutoSaveSerializable
import de.wulkanat.files.concept.AutoSerializableMutableList
import de.wulkanat.files.concept.SerializableObject
import de.wulkanat.files.concept.autoSerializableMutableListOf
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

object SuperroleConfig : SerializableObject<SuperroleConfig.Data>("superroles.json", Data(), Data.serializer()) {
    override var parent: AutoSaveSerializable? = null

    val botAdmin get() = instance.botAdmin
    val token get() = instance.token
    val status get() = instance.status
    val servers get() = instance.servers

    @Serializable
    data class Data(
        val botAdmin: Long = 1234,
        val token: String = "ABCDE",
        val status: String = "Reaction Roles!",

        val servers: AutoSerializableMutableList<String> = autoSerializableMutableListOf()
    ) : AutoSaveSerializable {
        @Transient override var parent: AutoSaveSerializable? = null

        override fun propagateParent() {
            servers.parent = this
            servers.propagateParent()
        }
    }
}