package com.example.pokemon.di.pokemonList;

import androidx.lifecycle.ViewModel;

import com.example.pokemon.di.ViewModelKey;
import com.example.pokemon.ui.pokemonlist.PokemonListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class PokemonListViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(PokemonListViewModel.class)
    public abstract ViewModel bindAuthViewModel(PokemonListViewModel viewModel);

}
