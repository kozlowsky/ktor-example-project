package service

import dao.GooglePropertyInfoRepository
import dao.PropertyInfoRepository
import google.api.downloadGeolocationOfProperties
import model.GooglePropertyInfo

class GooglePropertyInfoService(
    val googlePropertyInfoRepository: GooglePropertyInfoRepository,
    val propertyInfoRepository: PropertyInfoRepository
) {

    fun downloadGooglePropertyInfos() {
        googlePropertyInfoRepository.saveAll(downloadGeolocationOfProperties(propertyInfoRepository.findAll()))
    }

    fun getGoogleProperties(): List<GooglePropertyInfo> {
        return googlePropertyInfoRepository.findAll()
    }
}