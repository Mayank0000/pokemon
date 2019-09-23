package com.example.pokemon.network;
import com.example.pokemon.model.Details;
import com.example.pokemon.model.Search;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface SearchApi {

    @GET("pokemon/")
    Flowable<Search> getSearchList(@Query("offset") int offset,
                                   @Query("limit") int limit);

    @GET
    Flowable<ResponseBody> getPokemon(@Url String url);

    @GET
    Flowable<Details> getPokemonDetails(@Url String url);

//    @GET("/")
//    Flowable<Details> getMovieDetail(@Query("apikey") String apiKey,
//                                       @Query("i") String imDbId);

}
