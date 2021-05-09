package com.example.besttodo.core.di

import androidx.lifecycle.ViewModel
import co.zsmb.rainbowcake.dagger.ViewModelKey
import com.example.besttodo.core.viewmodels.TestTodosViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TestViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TestTodosViewModel::class)
    abstract fun bindTodosViewModel(todosViewModel: TestTodosViewModel): ViewModel

}