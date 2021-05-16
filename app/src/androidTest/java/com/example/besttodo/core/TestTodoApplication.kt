package com.example.besttodo.core

import co.zsmb.rainbowcake.dagger.RainbowCakeApplication
import co.zsmb.rainbowcake.dagger.RainbowCakeComponent
import com.example.besttodo.core.di.DaggerTestAppComponent
import com.example.besttodo.di.ApplicationModule

class TestTodoApplication : RainbowCakeApplication() {

    override lateinit var injector: RainbowCakeComponent

    override fun setupInjector() {
        injector = DaggerTestAppComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

}