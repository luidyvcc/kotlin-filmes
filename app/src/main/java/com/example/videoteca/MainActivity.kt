package com.example.videoteca

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_list_movies.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movies)

        toolbar.setTitleTextColor(Color.WHITE)
        setSupportActionBar(toolbar)

        val movies = arrayOf(
            "O Gabinete do Dr. Galigari",
            "Nosferatu",
            "Metropolis")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, movies)

        listViewMovies.setAdapter(adapter)

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


}
