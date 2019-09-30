package br.com.estudoprova.services

import br.com.estudoprova.app.EstudoProvaApp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInitializer {

    companion object {
        private val okHttpClient: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    var newRequest = chain.request()//.newBuilder()
                    val url = newRequest.url().newBuilder()
                        .addQueryParameter("apiKey", EstudoProvaApp.API_KEY).build()
                    newRequest = newRequest.newBuilder().url(url).build()
                    chain.proceed(newRequest)
                }
                .addInterceptor(HttpLoggingInterceptor().also { it ->
                    it.level = HttpLoggingInterceptor.Level.BODY
                })
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build()
        }
    }

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(EstudoProvaApp.URL_API)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    fun serviceSeries(): ServiceSeries {
        return retrofit.create(ServiceSeries::class.java)
    }


}