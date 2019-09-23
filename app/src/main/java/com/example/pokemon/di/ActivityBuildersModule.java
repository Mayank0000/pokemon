package com.example.pokemon.di;

import com.example.pokemon.di.detail.DetailModule;
import com.example.pokemon.di.detail.DetailScope;
import com.example.pokemon.di.detail.DetailViewModelsModule;
import com.example.pokemon.di.pokemonList.PokemonListModule;
import com.example.pokemon.di.pokemonList.PokemonListScope;
import com.example.pokemon.di.pokemonList.PokemonListViewModelsModule;
import com.example.pokemon.ui.details.DetailActivity;
import com.example.pokemon.ui.pokemonlist.PokemonListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @PokemonListScope
    @ContributesAndroidInjector(
            modules = {PokemonListViewModelsModule.class, PokemonListModule.class}
    )
    abstract PokemonListActivity contributeAuthActivity();

    @DetailScope
    @ContributesAndroidInjector(
            modules = {DetailViewModelsModule.class, DetailModule.class}

    )
    abstract DetailActivity contributeActivity();

}
