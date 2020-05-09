package db.table

import model.GooglePropertyInfo
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object GoogleProperty : Table() {
    val formattedAddress = varchar("formatted_address", 255)
    val latitude = double("latitude")
    val longitude = double("longitude")
    val placeId = varchar("place_id", 500)
    val urlId = integer("url_id")
    val id = uuid("_id")

    fun toGooglePropertyInfo(row: ResultRow): GooglePropertyInfo {
        return GooglePropertyInfo(
            row[formattedAddress],
            row[latitude],
            row[longitude],
            row[placeId],
            row[urlId],
            row[id]
        )
    }
}