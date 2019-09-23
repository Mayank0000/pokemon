package com.example.pokemon.di;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.pokemon.R;
import com.example.pokemon.network.SearchApi;
import com.example.pokemon.util.Constants;
import com.example.pokemon.util.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


// If we are  making different classes for interface then we should shift this to activity module class
    @Singleton
    @Provides
    static SearchApi provideSessionApi(Retrofit retrofit){
        return retrofit.create(SearchApi.class);
    }

    @Singleton
    @Provides
    static Repository getRepositoryInstance(SearchApi searchApi){
        return new Repository(searchApi);
    }


    @Singleton
    @Provides
    static RequestOptions provideRequestOptions(){
        return RequestOptions
                .placeholderOf(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);
    }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions){
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }


}
