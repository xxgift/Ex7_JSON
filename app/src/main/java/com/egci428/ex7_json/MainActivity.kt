package com.egci428.ex7_json

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.Toast
import com.egci428.ex7_json.Helper.Helper
import com.egci428.ex7_json.Models.Movie
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showMovieBtn.setOnClickListener {

            var asyncTask = object:AsyncTask<String, String, String>(){
                override fun onPreExecute() {
                    Toast.makeText(this@MainActivity, "Please wait...", Toast.LENGTH_SHORT).show()
                }

                override fun doInBackground(vararg p0: String?): String {
                    val helper = Helper()
                    val rand = (0..5).random()
                    return helper.getHTTPData("https://egco428-json-2850c.firebaseio.com/movies/$rand.json")
                }

                override fun onPostExecute(result: String?) {
                    val movieText = Gson().fromJson(result, Movie::class.java)
                    movieNameText.text = movieText.name

                    txtMovie.text = movieText.name
                    txtMovie.visibility = VISIBLE

                    starMovie.text = movieText.Star
                    starMovie.visibility = VISIBLE

                    yearMovie.text = movieText.Year
                    yearMovie.visibility = VISIBLE

                    genreMovie.text = movieText.Genre
                    genreMovie.visibility = VISIBLE

                }
            }
            asyncTask.execute()
        }
    }
}
