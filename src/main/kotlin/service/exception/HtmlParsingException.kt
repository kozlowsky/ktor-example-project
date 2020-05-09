package service.exception

import java.lang.Exception

class HtmlParsingException(message: String = "Couldn't parse the html!") : Exception(message) {}