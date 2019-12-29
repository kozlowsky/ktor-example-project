package server.routes

import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import service.HouseService

private val houseService = HouseService()

fun Route.house() {

    route("/houses") {

        get { call.respond(houseService.getHouses()) }
    }
}