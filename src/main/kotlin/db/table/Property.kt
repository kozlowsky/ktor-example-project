package db.table

import model.PropertyInfo
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

object Property : Table() {
    val fullPrice = varchar("full_price", 30)
    val landRegistry = varchar("land_registry", 30)
    val fireInsurance = varchar("fire_insurance", 30)
    val mortgage = varchar("mortgage", 30)
    val type = varchar("type", 20) //TODO: Change to enum
    val yearBuilt = varchar("year_built", 30)
    val size = double("size")
    val roomNumber = varchar("room_number", 30)
    val bedroomNumber = varchar("bedroom_number", 30)
    val areaNumber = varchar("area_number", 30)
    val bathroomNumber = varchar("bathroom_number", 30)
    val separateEntrance = varchar("separate_entrance", 30)
    val newBuilding = varchar("new_building", 30)
    val garage = varchar("garage", 30)
    val createdAt = varchar("created_at", 30)
    val updatedAt = varchar("updatad_at", 30)
    val address = varchar("address", 255)
    val postalCode = integer("postal_code")
    val city = varchar("city", 30)
    val url = varchar("url", 255)
    val urlId = integer("url_id")
    val id = uuid("id")

    fun toPropertyInfo(row: ResultRow): PropertyInfo {
        return PropertyInfo(
            row[fullPrice],
            row[landRegistry],
            row[fireInsurance],
            row[mortgage],
            row[type],
            row[yearBuilt],
            row[size],
            row[roomNumber],
            row[bedroomNumber],
            row[areaNumber],
            row[bathroomNumber],
            row[separateEntrance],
            row[newBuilding],
            row[garage],
            row[createdAt],
            row[updatedAt],
            row[address],
            row[postalCode],
            row[city],
            row[url],
            row[urlId],
            row[id]
        )
    }
}