package br.com.estudoprova.app

import android.app.Application

class EstudoProvaApp: Application {

    constructor() : super()

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        val URL_API = "https://www.omdbapi.com/"
        val API_KEY = "2f5cfd66"

        private var instance: EstudoProvaApp? = null

        fun getInstance(): EstudoProvaApp? {
            return instance
        }
    }
}