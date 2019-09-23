package com.example.pokemon.ui.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.pokemon.model.Details;
import com.example.pokemon.util.Repository;

import javax.inject.Inject;

//import com.example.search.model.Details;

public class DetailViewModel extends ViewModel {

    private static final String TAG = "DetailsViewModel";
    private Repository repository;
    private MediatorLiveData<Details> cachedUser = new MediatorLiveData<>();

    @Inject
    public DetailViewModel(Repository repository) {
        this.repository = repository;
    }

    public void getDetailList(String id){

        final LiveData<Details> source= repository.getPokemonDetails(id);

        cachedUser.setValue((Details)null);
        cachedUser.addSource(source, new Observer<Details>() {
            @Override
            public void onChanged(Details userSendMessageResource) {
                cachedUser.setValue(userSendMessageResource);
                cachedUser.removeSource(source);
            }
        });
    }

    public LiveData<Details> getLiveDataDetails(){
        return cachedUser;
    }

}

