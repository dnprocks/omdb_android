package br.com.estudoprova.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.estudoprova.R
import br.com.estudoprova.models.Seassion
import br.com.estudoprova.services.RetrofitInitializer
import br.com.estudoprova.ui.adapters.EpisodesAdapter
import kotlinx.android.synthetic.main.activity_seassion.*
import retrofit2.Call
import retrofit2.Response

class SeassionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seassion)

        searchSeasonSerie(
            intent.getStringExtra("title"),
            intent.getIntExtra("seassion", 1)
        )

    }

    fun searchSeasonSerie(title: String, season: Int) {
        var s = RetrofitInitializer().serviceSeries()
        var call = s.searchSeasonSerie(title, season)

        call.enqueue(object : retrofit2.Callback<Seassion> {

            override fun onResponse(call: Call<Seassion>?, response: Response<Seassion>?) {

                response?.let {

                    if (it.code() == 200) {

//                        Toast.makeText(
//                            this@SeassionActivity,
//                            it.body().Episodes[0].Title,
//                            Toast.LENGTH_LONG
//                        ).show()

                        recycleList.layoutManager = LinearLayoutManager(this@SeassionActivity)
                        recycleList.adapter =
                            EpisodesAdapter(this@SeassionActivity, it.body().Episodes)

                    }

                }

            }

            override fun onFailure(call: Call<Seassion>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }
}
