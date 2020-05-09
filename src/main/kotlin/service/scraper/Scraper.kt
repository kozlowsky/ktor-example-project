package service.scraper

import model.PropertyInfo

interface Scraper {

    fun downloadListOfPropertiesOnPage(page: Int): List<ScrapedProperty>

    fun downloadSingleProperty(scrapedProperty: ScrapedProperty): PropertyInfo?

    fun getNumberOfPages(): Int
}