package dao

import db.DatabaseFactory.dbQuery
import db.table.GoogleProperty
import kotlinx.coroutines.runBlocking
import model.GooglePropertyInfo
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import java.util.*

class GooglePropertyInfoRepositoryDB : GooglePropertyInfoRepository {
    override fun save(googlePropertyInfo: GooglePropertyInfo) {
        runBlocking {
            dbQuery {
                try {
                    GoogleProperty.insert {
                        it[formattedAddress] = googlePropertyInfo.formattedAddress
                        it[latitude] = googlePropertyInfo.latitude
                        it[longitude] = googlePropertyInfo.longitude
                        it[placeId] = googlePropertyInfo.placeId
                        it[urlId] = googlePropertyInfo.urlId
                        it[id] = UUID.randomUUID()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun saveAll(googlePropertyInfos: List<GooglePropertyInfo>) {
        googlePropertyInfos.forEach {
            save(it)
        }
    }

    override fun findAll(): List<GooglePropertyInfo> {
        return runBlocking {
            dbQuery {
                GoogleProperty.selectAll().map { GoogleProperty.toGooglePropertyInfo(it) }
            }
        }
    }
}