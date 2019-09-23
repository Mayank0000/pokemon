package com.example.pokemon.ui.pokemonlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.pokemon.model.UserModelClass;
import com.example.pokemon.ui.pokemonlist.datasource.UserDataSourceClass;
import com.example.pokemon.ui.pokemonlist.datasource.UserDataSourceFactory;
import com.example.pokemon.util.Repository;

import javax.inject.Inject;

public class PokemonListViewModel extends ViewModel {

    private static final String TAG = "PokemonListViewModel";
    private UserDataSourceFactory userDataSourceFactory;
    private LiveData<String> progressLoadStatus = new MutableLiveData<>();
    private PagedList.Config pagedListConfig;
    private MediatorLiveData<PagedList<UserModelClass>> cachedUser = new MediatorLiveData<>();

    @Inject
    public PokemonListViewModel(Repository repository, PagedList.Config pagedListConfig) {
        this.pagedListConfig = pagedListConfig;
        userDataSourceFactory = new UserDataSourceFactory(repository);
        initializePaging();
    }


//    public LiveData<List<UserModelClass>> getList(){
//        return  repository.getList();
//    }

    private void initializePaging() {
        final LiveData<PagedList<UserModelClass>> source = new LivePagedListBuilder<>(userDataSourceFactory, pagedListConfig)
                .build();
        progressLoadStatus = Transformations.switchMap(userDataSourceFactory.getMutableLiveData(),
                UserDataSourceClass::getProgressLiveStatus);

        cachedUser.setValue((PagedList<UserModelClass>) null);
        cachedUser.addSource(source, userSendMessageResource -> {
            cachedUser.setValue(userSendMessageResource);
            cachedUser.removeSource(source);
        });

    }

    public LiveData<String> getProgressLoadStatus() {
        return progressLoadStatus;
    }

    public LiveData<PagedList<UserModelClass>> getListLiveData() {
        return cachedUser;
    }
}

