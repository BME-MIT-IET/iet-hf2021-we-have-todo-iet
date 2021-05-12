package com.example.besttodo.core.di

import co.zsmb.rainbowcake.dagger.RainbowCakeComponent
import co.zsmb.rainbowcake.dagger.RainbowCakeModule
import com.example.besttodo.di.ApplicationModule
import com.example.besttodo.di.DataSourceModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RainbowCakeModule::class,
        TestViewModelModule::class,
        ApplicationModule::class,
        TestDataModule::class,
        DataSourceModule::class
    ]
)
interface TestAppComponent : RainbowCakeComponent