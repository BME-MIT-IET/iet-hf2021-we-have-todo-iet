package com.example.besttodo.core

import androidx.room.Room
import co.zsmb.rainbowcake.dagger.RainbowCakeApplication
import co.zsmb.rainbowcake.dagger.RainbowCakeComponent
import com.example.besttodo.core.di.DaggerTestAppComponent
import com.example.besttodo.di.ApplicationModule
import com.example.besttodo.data.disk.AppDatabase

class TestTodoApplication : RainbowCakeApplication() {

    override lateinit var injector: RainbowCakeComponent

    override fun setupInjector() {
        injector = DaggerTestAppComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

}