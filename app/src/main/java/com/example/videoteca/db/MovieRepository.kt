package com.example.videoteca.db

import android.content.Context
import com.example.videoteca.db.ConstantDb.MOVIES_DB_TABLE
import org.jetbrains.anko.db.*
import timber.log.Timber

class MovieRepository (val context: Context) {

    fun findAll() : ArrayList<Movie> = context.database.use {
        val movies = ArrayList<Movie>()

        select(ConstantDb.MOVIES_DB_TABLE, "id", "title", "release")
            .parseList(object: MapRowParser<List<Movie>> {
                override fun parseRow(columns: Map<String, Any?>): List<Movie> {
                    val id = columns.getValue("id")
                    val title = columns.getValue("title")
                    val release = columns.getValue("release")

                    val contato = Movie(
                        id.toString()?.toLong(),
                        title?.toString(),
                        release?.toString()
                    )
                    movies.add(contato)
                    return movies
                }
            })

        movies
    }



    fun create(movie: Movie) = context.database.use {
        insert(
            ConstantDb.MOVIES_DB_TABLE,
            "title" to movie.title,
            "release" to movie.release)
    }


    fun update(movie: Movie) = context.database.use {
        val updateResult = update(
            MOVIES_DB_TABLE,
            "title" to movie.title)
            .whereArgs("id = {id}","id" to movie.id).exec()

        Timber.d("Update result code is $updateResult")
    }


    fun delete(id: Long) = context.database.use {
        delete(MOVIES_DB_TABLE, "id = {movieId}", *arrayOf("movieId" to id))
    }


}