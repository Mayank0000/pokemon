package com.example.pokemon.di.detail;

import androidx.lifecycle.ViewModel;

import com.example.pokemon.di.ViewModelKey;
import com.example.pokemon.ui.details.DetailViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class DetailViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    public abstract ViewModel bindAuthViewModel(DetailViewModel viewModel);

}
