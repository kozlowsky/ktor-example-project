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

data class House(val key: String, val value: String)

private val houseService = HouseService()

fun Route.house() {

    route("house") {

        get { call.respond(houseService.getHouses()) }
        post {
            val house = call.receive<House>()

            try {
                houseService.addNewHouse(house.key, house.value)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Key value already exist!")
            }

            call.respond(HttpStatusCode.Created)
        }
    }
}