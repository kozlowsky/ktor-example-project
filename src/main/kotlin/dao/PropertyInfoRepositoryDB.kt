package dao

import db.DatabaseFactory.dbQuery
import db.table.Property
import kotlinx.coroutines.runBlocking
import model.PropertyInfo
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.util.*

class PropertyInfoRepositoryDB : PropertyInfoRepository {
    override fun save(propertyInfo: PropertyInfo) {
        saveProperty(propertyInfo)
    }

    override fun saveAll(propertyInfos: List<PropertyInfo>) {
        propertyInfos.forEach {
            saveProperty(it)
        }
    }

    override fun findById(id: UUID): PropertyInfo? {
        try {
            return runBlocking {
                dbQuery {
                    Property.select { Property.id eq id }.map { Property.toPropertyInfo(it) }.firstOrNull()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    override fun findByUrl(url: String): PropertyInfo? {
        try {
            return runBlocking {
                dbQuery {
                    Property.select { Property.url eq url }.map { Property.toPropertyInfo(it) }.firstOrNull()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override fun findAll(): List<PropertyInfo> {
        return runBlocking {
            dbQuery {
                Property.selectAll().map { Property.toPropertyInfo(it) }
            }
        }
    }

    private fun saveProperty(propertyInfo: PropertyInfo) {
        runBlocking {
            dbQuery {
                Property.insert {
                    it[fullPrice] = propertyInfo.fullPrice
                    it[landRegistry] = propertyInfo.landRegistry
                    it[fireInsurance] = propertyInfo.fireInsurance
                    it[mortgage] = propertyInfo.mortgage
                    it[type] = propertyInfo.type
                    it[yearBuilt] = propertyInfo.yearBuilt
                    it[size] = propertyInfo.size
                    it[roomNumber] = propertyInfo.roomNumber
                    it[bedroomNumber] = propertyInfo.bedroomNumber
                    it[areaNumber] = propertyInfo.areaNumber
                    it[bathroomNumber] = propertyInfo.bathroomNumber
                    it[separateEntrance] = propertyInfo.separateEntrance
                    it[newBuilding] = propertyInfo.newBuilding
                    it[garage] = propertyInfo.garage
                    it[createdAt] = propertyInfo.createdAt
                    it[updatedAt] = propertyInfo.updatedAt
                    it[address] = propertyInfo.address
                    it[postalCode] = propertyInfo.postalCode
                    it[city] = propertyInfo.city
                    it[url] = propertyInfo.url
                    it[urlId] = propertyInfo.urlId
                    it[id] = UUID.randomUUID()
                }
            }
        }
    }
}