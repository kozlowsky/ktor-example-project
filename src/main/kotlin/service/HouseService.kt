package service


class HouseService {

    private val map = mutableMapOf("home" to "house")

    fun getHouses(): Map<String, String> {
        return map
    }

    fun addNewHouse(key: String?, value: String?) {
        if (key.isNullOrEmpty() || value.isNullOrEmpty()) throw Exception()
        if (map.containsKey(key)) throw Exception()

        map[key] = value
    }
}

