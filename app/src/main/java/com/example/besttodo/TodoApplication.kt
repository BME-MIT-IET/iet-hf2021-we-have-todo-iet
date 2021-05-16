package com.example.besttodo

import co.zsmb.rainbowcake.dagger.RainbowCakeApplication
import co.zsmb.rainbowcake.dagger.RainbowCakeComponent
import com.example.besttodo.di.ApplicationModule
import com.example.besttodo.di.DaggerAppComponent

class TodoApplication : RainbowCakeApplication() {

    override lateinit var injector: RainbowCakeComponent

    override fun setupInjector() {
        injector = DaggerAppComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

}