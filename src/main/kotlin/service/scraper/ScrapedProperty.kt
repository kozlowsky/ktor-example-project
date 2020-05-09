package service.scraper

data class ScrapedProperty(
    val postalCode: Int,
    val city: String,
    val url: String,
    val urlId: Int
)