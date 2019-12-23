package server

import io.ktor.routing.Route
import server.routes.house

fun Route.setup() {
    house()
}