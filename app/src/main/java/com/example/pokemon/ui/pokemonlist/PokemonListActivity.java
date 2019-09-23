package com.example.pokemon.ui.pokemonlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pokemon.R;
import com.example.pokemon.databinding.PagingDemoLayoutBinding;
import com.example.pokemon.ui.details.DetailActivity;
import com.example.pokemon.util.Constants;
import com.example.pokemon.viewmodels.ViewModelProviderFactory;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class PokemonListActivity extends DaggerAppCompatActivity implements onRowClick {

    @Inject ViewModelProviderFactory viewModelFactory;
    @Inject DividerItemDecoration itemDecorator;
    private PageListAdapter adapter;
    private PagingDemoLayoutBinding binding;
    private PokemonListViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  DataBindingUtil.setContentView(this, R.layout.paging_demo_layout);
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PokemonListViewModel.class);
        init();
    }


    private void init() {
        adapter = new PageListAdapter(this);
        binding.list.setLayoutManager(new LinearLayoutManager(this));
        binding.list.setAdapter(adapter);
        itemDecorator.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.divider)));
        binding.list.addItemDecoration(itemDecorator);
        if (!Constants.checkInternetConnection(this)) {
            Snackbar.make(findViewById(android.R.id.content), Constants.CHECK_NETWORK_ERROR, Snackbar.LENGTH_SHORT)
                    .show();
        }
        viewModel.getListLiveData().observe(this, pagedList -> adapter.submitList(pagedList));
        viewModel.getProgressLoadStatus().observe(this, status -> {
            if (Objects.requireNonNull(status).equalsIgnoreCase(Constants.LOADING)) {
                binding.progressBar.setVisibility(View.VISIBLE);
            } else if (status.equalsIgnoreCase(Constants.LOADED)) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });

    }


    @Override
    public void onItemClick(String id) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("url",id);
        startActivity(intent);
    }
}
