package br.com.estudoprova.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.estudoprova.R
import br.com.estudoprova.models.Series
import br.com.estudoprova.services.RetrofitInitializer
import com.afollestad.materialdialogs.MaterialDialog
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSearch.setOnClickListener {

            if (searchTitle.text.isNotEmpty()) {
                searchSerie()
            }

        }
    }

    fun searchSerie() {

        progressBar.visibility = View.VISIBLE

        var s = RetrofitInitializer().serviceSeries()
        var call = s.searchSeriesByName(searchTitle.text.toString())

        call.enqueue(object : retrofit2.Callback<Series> {

            override fun onResponse(call: Call<Series>?, response: Response<Series>?) {

                response?.let {

                    if (it.code() == 200) {

                        if (it.body().Response) {

                            var title = findViewById<TextView>(R.id.title)
                            var genre = findViewById<TextView>(R.id.genre)
                            var rating = findViewById<TextView>(R.id.rating)
                            var votes = findViewById<TextView>(R.id.votes)
                            var bntSession = findViewById<Button>(R.id.btnSessionFind)

                            title.text = it.body().Title
                            genre.text = it.body().Genre
                            rating.text = it.body().imdbRating
                            votes.text = it.body().imdbVotes

                            Glide
                                .with(this@MainActivity)
                                .load(it.body().Poster)
                                .into(poster)

                            bntSession.setOnClickListener {
                                var intent = Intent(this@MainActivity, SeassionActivity::class.java)
                                intent.putExtra("title", title.text.toString())
                                intent.putExtra("seassion", 1)
                                startActivity(intent)
                            }

                            infoSeries.visibility = View.VISIBLE
                            progressBar.visibility = View.GONE

                        } else {

                            MaterialDialog.Builder(this@MainActivity)
                                .title(getString(R.string.ops))
                                .content(getString(R.string.search_not_found))
                                .positiveText(getString(R.string.positive_ok))
                                .show()

                            progressBar.visibility = View.GONE
                            infoSeries.visibility = View.GONE

                        }


                    }

                }

            }

            override fun onFailure(call: Call<Series>?, t: Throwable?) {
                MaterialDialog.Builder(this@MainActivity)
                    .title(getString(R.string.ops))
                    .content(getString(R.string.search_fail))
                    .positiveText(getString(R.string.positive_ok))
                    .show()

                progressBar.visibility = View.GONE
                infoSeries.visibility = View.GONE
            }
        })

    }


}
