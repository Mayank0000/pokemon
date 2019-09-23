package com.example.pokemon.ui.pokemonlist.datasource;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.pokemon.model.UserModelClass;
import com.example.pokemon.util.Repository;


public class UserDataSourceFactory extends DataSource.Factory<Integer, UserModelClass> {

    private MutableLiveData<UserDataSourceClass> liveData;
    private Repository repository;

    public UserDataSourceFactory(Repository repository) {
        this.repository = repository;
        liveData = new MutableLiveData<>();
    }

    public MutableLiveData<UserDataSourceClass> getMutableLiveData() {
        return liveData;
    }

    @NonNull
    @Override
    public DataSource<Integer, UserModelClass> create() {
        UserDataSourceClass dataSourceClass = new UserDataSourceClass(repository);
        liveData.postValue(dataSourceClass);
        return dataSourceClass;
    }
}
