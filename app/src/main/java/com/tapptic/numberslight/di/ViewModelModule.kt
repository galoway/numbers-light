package com.tapptic.numberslight.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tapptic.numberslight.ui.detail.DetailViewModel
import com.tapptic.numberslight.ui.list.ListViewModel
import com.vp.daggeraddons.DaggerViewModelFactory
import com.vp.daggeraddons.ViewModelKey
import dagger.Binds
import dagger.multibindings.IntoMap
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindDaggerViewModelFactory(daggerViewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(listViewModel: ListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel
}