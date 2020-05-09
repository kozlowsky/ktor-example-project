package model

import db.table.Property
import org.jetbrains.exposed.sql.ResultRow
import java.util.*

data class GooglePropertyInfo(
    val formattedAddress: String,
    val latitude: Double,
    val longitude: Double,
    val placeId: String,
    val urlId: Int,
    val id: UUID = UUID.randomUUID()
)

