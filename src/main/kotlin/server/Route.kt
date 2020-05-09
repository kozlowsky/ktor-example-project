package server

import io.ktor.routing.Route
import server.routes.property

fun Route.setup() {
    property()
}