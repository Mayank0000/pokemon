package com.example.pokemon.di.pokemonList;


import android.app.Application;

import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class PokemonListModule {

    @PokemonListScope
    @Provides
    static PagedList.Config getPageList() {
        return new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .setPageSize(20).build();
    }

    @PokemonListScope
    @Provides
    static DividerItemDecoration getDecoration(Application application){
        return new DividerItemDecoration(application, DividerItemDecoration.VERTICAL);
    }

//    @PokemonListScope
//    @Provides
//    static SearchListAdapter getAdapter(){
//        return new SearchListAdapter();
//}
}


















