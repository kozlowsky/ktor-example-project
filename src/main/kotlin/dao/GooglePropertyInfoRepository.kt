package dao

import model.GooglePropertyInfo

interface GooglePropertyInfoRepository {
    fun save(googlePropertyInfo: GooglePropertyInfo)
    fun saveAll(googlePropertyInfos: List<GooglePropertyInfo>)
    fun findAll(): List<GooglePropertyInfo>
}