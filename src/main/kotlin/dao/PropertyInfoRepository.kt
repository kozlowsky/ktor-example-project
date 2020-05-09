package dao

import model.PropertyInfo
import java.util.*

interface PropertyInfoRepository {

    fun save(propertyInfo: PropertyInfo)

    fun saveAll(propertyInfos: List<PropertyInfo>)

    fun findById(id: UUID): PropertyInfo?

    fun findByUrl(url: String): PropertyInfo?

    fun findAll(): List<PropertyInfo>
}