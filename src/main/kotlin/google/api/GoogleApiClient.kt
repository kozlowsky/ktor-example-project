package google.api

import model.GooglePropertyInfo
import model.PropertyInfo
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

private val GOOGLE_KEY: String = "AIzaSyDLSzRF0e_vL2O9ga2oOM3eox-WgOS1fmM"

private val GOOGLE_URL: String = "https://maps.googleapis.com/maps/api/geocode/json?"

fun downloadGeolocationOfProperties(properties: List<PropertyInfo>) : List<GooglePropertyInfo> {
    return properties.mapNotNull {
        downloadGeolocation(it)
    }
}

private fun downloadGeolocation(property: PropertyInfo) : GooglePropertyInfo? {
    try {
        val jsonObject =
            (((khttp.get("${GOOGLE_URL}address=${property.address}&key=${GOOGLE_KEY}").jsonObject["results"] as JSONArray)[0]) as JSONObject)
        val googlePropertyInfo = GooglePropertyInfo(
            jsonObject["formatted_address"].toString(),
            ((jsonObject["geometry"] as JSONObject)["location"] as JSONObject)["lat"].toString().toDouble(),
            ((jsonObject["geometry"] as JSONObject)["location"] as JSONObject)["lng"].toString().toDouble(),
            jsonObject["place_id"].toString(),
            property.urlId,
            UUID.randomUUID()
        )
        println("Google Info of ${property.address} was fetched!")
        return googlePropertyInfo
    } catch (e: Exception) {
        println("Geolocation of ${property.address} was not found!")
        return null
    }
}