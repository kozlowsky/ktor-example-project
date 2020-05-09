package service.scraper.mbl

import model.PropertyInfo
import org.jsoup.Jsoup
import org.slf4j.LoggerFactory
import service.exception.HtmlParsingException
import service.scraper.ScrapedProperty
import service.scraper.Scraper
import java.io.IOException

class MblPropertyScraperOnline : Scraper {

    private val BASE_HTTP: String = "http://www.mbl.is/"

    private val HOUSE_FLAT_HTTP_SEARCH: String = "fasteignir/leit/?q=608ec84b95cae76e291ad04ac0f31b40&page="

    override fun downloadListOfPropertiesOnPage(page: Int): List<ScrapedProperty> {
        println("Scraping page #$page")
        try {
            return Jsoup.connect("$BASE_HTTP$HOUSE_FLAT_HTTP_SEARCH$page").get().run {
                select("div.single-realestate")
                    .map {
                        val postalCodeCity = it.select("h5").text().split(" ")
                        ScrapedProperty(
                            postalCodeCity[0].toInt(),
                            postalCodeCity[1],
                            it.select("a").attr("href"),
                            it.select("a").attr("href").split("/")[3].toInt()
                        )
                    }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw HtmlParsingException("Couldn't parse the list of properties on page $page")
        }
    }

    override fun downloadSingleProperty(scrapedProperty: ScrapedProperty): PropertyInfo? {
        println("Scraping from ${scrapedProperty.url}")
        try {
            return Jsoup.connect("${BASE_HTTP}${scrapedProperty.url}").get().run {
                selectFirst("div.fasteignir").let {
                    PropertyInfo(
                        it.select(".table-striped tbody tr td.value")[0].text().split(" ")[0].replace(".", ""),
                        it.select(".table-striped tbody tr td.value")[1].text().split(" ")[0].replace(".", ""),
                        it.select(".table-striped tbody tr td.value")[2].text().split(" ")[0].replace(".", ""),
                        it.select(".table-striped tbody tr td.value")[3].text().split(" ")[0].replace(".", ""),
                        it.select(".table-striped tbody tr td.value")[4].text(),
                        it.select(".table-striped tbody tr td.value")[5].text(),
                        it.select(".table-striped tbody tr td.value")[6].text().split(" ")[0].toDouble(),
                        it.select(".table-striped tbody tr td.value")[7].text(),
                        it.select(".table-striped tbody tr td.value")[8].text(),
                        it.select(".table-striped tbody tr td.value")[9].text(),
                        it.select(".table-striped tbody tr td.value")[10].text(),
                        it.select(".table-striped tbody tr td.value")[11].text(),
                        it.select(".table-striped tbody tr td.value")[12].text(),
                        it.select(".table-striped tbody tr td.value")[13].text(),
                        it.select(".table-striped tbody tr td.value")[14].text(),
                        it.select(".table-striped tbody tr td.value")[15].text(),
                        it.select(".realestate-headline .realestate-headline-address strong").text(),
                        scrapedProperty.postalCode,
                        scrapedProperty.city,
                        scrapedProperty.url,
                        scrapedProperty.urlId
                    )
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
            throw HtmlParsingException("Couldn't scrape Property $scrapedProperty")
        }
        return null
    }

    override fun getNumberOfPages(): Int {
        //TODO: Throw exception that couldn't parse number of pages!
        return Jsoup.connect("${BASE_HTTP}${HOUSE_FLAT_HTTP_SEARCH}0").get().run {
            select("div.info > small").text().split(" ")[7].toInt()
        }
    }
}