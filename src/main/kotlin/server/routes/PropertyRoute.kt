package server.routes

import dao.GooglePropertyInfoRepository
import dao.GooglePropertyInfoRepositoryDB
import dao.PropertyInfoRepository
import dao.PropertyInfoRepositoryDB
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import service.GooglePropertyInfoService
import service.PropertyInfoService
import service.scraper.mbl.MblPropertyScraperOnline

private val scraper = MblPropertyScraperOnline()
private val propertyRepository: PropertyInfoRepository = PropertyInfoRepositoryDB()
private val googlePropertyRepository: GooglePropertyInfoRepository = GooglePropertyInfoRepositoryDB()
private val propertyInfoService = PropertyInfoService(scraper, propertyRepository)
private val googlePropertyInfoService = GooglePropertyInfoService(googlePropertyRepository, propertyRepository)

fun Route.property() {

    route("/properties") {
        get {
            call.respond(propertyInfoService.getProperties())
        }
    }
    route("/properties/google") {
        post {
            call.respond(googlePropertyInfoService.downloadGooglePropertyInfos())
        }
        get {
            call.respond(googlePropertyInfoService.getGoogleProperties())
        }
    }
    route("/properties/scrape") {
        post { call.respond(propertyInfoService.downloadProperties()) }
    }
}
