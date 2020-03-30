package xyz.jonthn.favmovies.model.apis

import java.io.IOException

class ApiException(message: String) : IOException(message)
class NoInternetException() : IOException() {
    override val message: String
        get() =
            "Sin internet, por favor revisa tu conexión WIFi o Datos"
}