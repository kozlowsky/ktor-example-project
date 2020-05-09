package server

import db.DatabaseFactory
import io.ktor.application.install
import io.ktor.features.CORS
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun startServer() {
    embeddedServer(Netty, 8081) {
        install(ContentNegotiation) {
            jackson {}
        }
        install(CallLogging)
        install(CORS) {
            anyHost()
        }
        routing {
            setup()
        }
        DatabaseFactory.init()
    }.start()
}