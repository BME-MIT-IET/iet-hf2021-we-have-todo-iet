package com.example.besttodo.core.di

import co.zsmb.rainbowcake.dagger.RainbowCakeComponent
import co.zsmb.rainbowcake.dagger.RainbowCakeModule
import com.example.besttodo.di.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RainbowCakeModule::class,
        TestViewModelModule::class,
        ApplicationModule::class,
        TestDataModule::class
    ]
)
interface TestAppComponent : RainbowCakeComponent