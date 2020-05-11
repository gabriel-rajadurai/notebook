package com.gabriel.notebook

import android.app.Application
import com.gabriel.data.RoomModule

class NotesApplication : Application() {

    lateinit var appGraph: ApplicationGraph

    override fun onCreate() {
        super.onCreate()
        appGraph = DaggerApplicationGraph
            .builder()
            .applicationModule(
                ApplicationModule(this)
            )
            .roomModule(
                RoomModule((this))
            )
            .build()
    }
}