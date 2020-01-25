package com.example.videoteca

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.videoteca.db.Movie
import com.example.videoteca.db.MovieRepository
import kotlinx.android.synthetic.main.activity_list_movies.*

class MainActivity : AppCompatActivity() {
    private var movies:ArrayList<Movie>? = null
    private var moviesSelecionado: Movie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movies)

        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.listar -> {
                Toast.makeText(this, "Você já esta na listagem, seu asno!", Toast.LENGTH_LONG).show()
                return false
            }

            R.id.adicionar -> {
                val intent = Intent(this, FormActivity::class.java)
                startActivity(intent)
                return false
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        val movies = MovieRepository(this).findAll()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, movies)
        listViewMovies?.adapter = adapter
        adapter.notifyDataSetChanged()

        listViewMovies.setOnItemClickListener { _, _, position, id ->
            val intent = Intent(this, FormActivity::class.java)
            intent.putExtra("movie", movies?.get(position))
            startActivity(intent)
        }

    }


}
