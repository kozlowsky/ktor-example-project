package service

import dao.PropertyInfoRepository
import model.PropertyInfo
import service.scraper.ScrapedProperty
import service.scraper.Scraper

class PropertyInfoService(
    private val scraper: Scraper,
    private val propertyInfoRepository: PropertyInfoRepository
) {


    fun downloadProperties() {
        var foundDuplicate = false
        var currentPage = 1
        val pagesFound = scraper.getNumberOfPages()
        while (!foundDuplicate && currentPage < pagesFound) {
            val scrapedProperties = scraper.downloadListOfPropertiesOnPage(currentPage)
            val index = getIndexOfFirstDuplicate(scrapedProperties)
            if (index > -1) {
                propertyInfoRepository.saveAll(
                    scrapedProperties.subList(
                        0,
                        index
                    ).mapNotNull { scraper.downloadSingleProperty(it) })
                foundDuplicate = !foundDuplicate
                println("Found the duplicate! ${scrapedProperties[index]}")
            } else {
                try {
                    propertyInfoRepository.saveAll(scrapedProperties.mapNotNull { scraper.downloadSingleProperty(it) })
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                currentPage++
            }
        }
    }

    fun getProperties(): List<PropertyInfo> {
        return propertyInfoRepository.findAll()
    }

    private fun getIndexOfFirstDuplicate(properties: List<ScrapedProperty>): Int {
        return properties.indexOfFirst { propertyInfoRepository.findByUrl(it.url) !== null }
    }
}

