package com.example.pokemon.util;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import com.example.pokemon.model.Details;
import com.example.pokemon.model.Search;
import com.example.pokemon.network.SearchApi;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


public class Repository {

    private SearchApi searchApi;

    @Inject
    public Repository(SearchApi searchApi){
        this.searchApi = searchApi;
    }


    public Flowable<Search> getSearchResult(int index){
        return searchApi.getSearchList(index,20);
    }

    public Flowable<ResponseBody> getPokemon(String url){
        return searchApi.getPokemon(url);
    }
    public Flowable<Details> getPokemonDetail(String url){
        return searchApi.getPokemonDetails(url);
    }


    public LiveData<Details> getPokemonDetails(final String id){
       return  LiveDataReactiveStreams.fromPublisher(getPokemonDetail(id)
               .onErrorReturn(throwable-> null)
                .subscribeOn(Schedulers.io()));
    }

}
