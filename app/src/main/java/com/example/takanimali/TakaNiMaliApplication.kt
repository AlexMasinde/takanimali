package com.example.takanimali

import android.app.Application
import com.example.takanimali.data.local.TakaNiMaliDatabase
import com.example.takanimali.di.AppContainer
import com.example.takanimali.di.DefaultAppContainer

class TakaNiMaliApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    val database: TakaNiMaliDatabase by lazy { TakaNiMaliDatabase.getDatabase((this)) }
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}