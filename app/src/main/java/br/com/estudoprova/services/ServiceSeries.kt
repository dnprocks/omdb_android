package br.com.estudoprova.services

import br.com.estudoprova.models.Seassion
import br.com.estudoprova.models.Series
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceSeries {

    @GET("/default.aspx")
    fun searchSeriesByName(@Query("t") title: String): Call<Series>

    @GET("/default.aspx")
    fun searchSeasonSerie(@Query("t") title: String, @Query("Season") session: Int): Call<Seassion>
}