package model

import java.util.*

data class PropertyInfo(
    val fullPrice: String,
    val landRegistry: String,
    val fireInsurance: String,
    val mortgage: String,
    val type: String, //TODO: Change to enum
    val yearBuilt: String,
    val size: Double,
    val roomNumber: String,
    val bedroomNumber: String,
    val areaNumber: String,
    val bathroomNumber: String,
    val separateEntrance: String,
    val newBuilding: String,
    val garage: String,
    val createdAt: String,
    val updatedAt: String,
    val address: String,
    val postalCode: Int,
    val city: String,
    val url: String,
    val urlId: Int,
    val id: UUID = UUID.randomUUID()
)