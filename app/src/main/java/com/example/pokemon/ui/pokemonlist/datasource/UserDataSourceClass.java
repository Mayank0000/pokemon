package com.example.pokemon.ui.pokemonlist.datasource;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import com.example.pokemon.model.Search;
import com.example.pokemon.model.UserModelClass;
import com.example.pokemon.util.Constants;
import com.example.pokemon.util.Repository;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.ResponseBody;

public class UserDataSourceClass extends PageKeyedDataSource<Integer, UserModelClass> {

    private Repository repository;
    private int sourceIndex;
    private MutableLiveData<String> progressLiveStatus;

    public UserDataSourceClass(Repository repository) {
        this.repository = repository;
        progressLiveStatus = new MutableLiveData<>();
        sourceIndex = 0;
    }


    public MutableLiveData<String> getProgressLiveStatus() {
        return progressLiveStatus;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadInitial(@NonNull PageKeyedDataSource.LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, UserModelClass> callback) {

        repository.getSearchResult(sourceIndex*20)
                .doOnSubscribe( disposable -> {
                            progressLiveStatus.postValue(Constants.LOADING);
                        }
                )
                .subscribe((Search searchList)->{
                    progressLiveStatus.postValue(Constants.LOADED);
                    ArrayList<UserModelClass> arrayList = new ArrayList<>();
                    for (int i = 0; i < searchList.getResults().size(); i++) {
                        AtomicReference<String> finalName = new AtomicReference<>();
                        repository.getPokemon(searchList.getResults().get(i).getUrl())
                                .subscribe((ResponseBody response)->{
                                    JSONObject jsonObject = new JSONObject(response.string());
                                    finalName.set(jsonObject.getJSONObject("sprites").getString("front_default"));
                                },throwable -> {});
                        arrayList.add(new UserModelClass(searchList.getResults().get(i).getName(),
                                finalName.get(),searchList.getResults().get(i).getUrl()));

                    }

                    sourceIndex = sourceIndex + 1;
                    callback.onResult(arrayList,null,sourceIndex);

                }, throwable -> {
                    progressLiveStatus.postValue(Constants.LOADED);
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, UserModelClass> callback) {

    }

    @SuppressLint("CheckResult")
    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, UserModelClass> callback) {
        repository.getSearchResult(sourceIndex*20)
                .doOnSubscribe( disposable -> {
                            progressLiveStatus.postValue(Constants.LOADING);
                        }
                )
                .subscribe((Search searchList)->{
                    progressLiveStatus.postValue(Constants.LOADED);
                    ArrayList<UserModelClass> arrayList = new ArrayList<>();
                    for (int i = 0; i < searchList.getResults().size(); i++) {
                        AtomicReference<String> finalName = new AtomicReference<>();
                        repository.getPokemon(searchList.getResults().get(i).getUrl())
                                .subscribe((ResponseBody response)->{
                                    JSONObject jsonObject = new JSONObject(response.string());
                                    finalName.set(jsonObject.getJSONObject("sprites").getString("front_default"));
                                },throwable -> {});
                        arrayList.add(new UserModelClass(searchList.getResults().get(i).getName(),
                                finalName.get(), searchList.getResults().get(i).getUrl()));

                    }
                    sourceIndex = sourceIndex + 1;
                    callback.onResult(arrayList,sourceIndex);
                }, throwable -> {
                    progressLiveStatus.postValue(Constants.LOADED);
                });
    }

}
