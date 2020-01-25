package com.example.videoteca

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import com.example.videoteca.db.Movie
import com.example.videoteca.db.MovieRepository
import kotlinx.android.synthetic.main.activity_form_movie.*
import java.text.SimpleDateFormat
import java.util.*

class FormActivity : AppCompatActivity() {

    var cal = Calendar.getInstance()
    var movie: Movie? = null
    var release: Button? = null

    //private var txtName: EditText? = null
    //private var txtRelease: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_movie)

        setSupportActionBar(toolbar_child)

        val ab = supportActionBar
        ab!!.setDisplayHomeAsUpEnabled(true)

        if (intent?.getSerializableExtra("contato") != null) {
            movie = intent?.getSerializableExtra("contato") as Movie
            titleField?.setText(movie?.title)
        }else{
            movie = Movie()
        }

        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        releaseField.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@FormActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })

        btnCadastro?.setOnClickListener {
            movie?.title = titleField?.text.toString()
            movie?.release = releaseField?.text.toString()

            if(movie?.id?.toInt() == 0){
                MovieRepository(this).create(movie!!)
            }else{
                MovieRepository(this).update(movie!!)
            }
            finish()
        }
    }

    private fun updateDateInView() {
        val myFormat = "dd/MM/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        releaseField.text = sdf.format(cal.getTime())
    }

    override fun onResume() {
        super.onResume()
        val intent = intent
        if (intent != null) {
            if (intent.getSerializableExtra("movie") != null) {
                movie = intent.getSerializableExtra("movie") as Movie

                titleField?.setText(movie?.title)
                releaseField?.setText(movie?.release)
            } else {
                movie = Movie()
            }
        }

    }

}

