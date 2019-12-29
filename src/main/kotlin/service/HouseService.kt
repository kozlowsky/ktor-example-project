package service


class HouseService {

    private val map = mutableMapOf("home" to "house")

    fun getHouses(): Map<String, String> {
        return map
    }
}

