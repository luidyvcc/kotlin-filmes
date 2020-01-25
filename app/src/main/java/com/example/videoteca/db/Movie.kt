package com.example.videoteca.db

import java.io.Serializable

class Movie (
    var id: Long = 0,
    var title: String? = null,
    var release: String? = null ) : Serializable {

    override fun toString(): String {
        return title.toString()
    }

}