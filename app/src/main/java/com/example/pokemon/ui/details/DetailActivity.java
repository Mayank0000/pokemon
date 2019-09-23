package com.example.pokemon.ui.details;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.example.pokemon.R;
import com.example.pokemon.databinding.ActivityDetailsBinding;
import com.example.pokemon.model.Move;
import com.example.pokemon.model.Move_;
import com.example.pokemon.model.Type;
import com.example.pokemon.model.Type_;
import com.example.pokemon.util.Constants;
import com.example.pokemon.viewmodels.ViewModelProviderFactory;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class DetailActivity extends DaggerAppCompatActivity {

    private DetailViewModel viewModel;
    @Inject
    RequestManager requestManager;
    @Inject
    ViewModelProviderFactory providerFactory;
    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);
        viewModel = ViewModelProviders.of(this, providerFactory).get(DetailViewModel.class);
        viewModel.getDetailList(getIntent().getStringExtra("url"));
        setProgressBar(true);
        subscribeObservers();
    }

    private void subscribeObservers() {
        viewModel.getLiveDataDetails().observe(this, details -> {
            if (details != null) {
                binding.name.setText(details.getName());
                String ability = "";
                for(int i=0;i<details.getAbilities().size();i++) {
                    ability = String.format("%s%s, ", ability, details.getAbilities().get(i).getAbility().getName());
                }
                binding.ability.setText(ability);
                String type = "";
                for(int i=0;i<details.getTypes().size();i++) {
                    type = String.format("%s%s, ", type, ((Type_)((Type)details.getTypes().get(i)).getType()).getName());
                }

                binding.types.setText(type);
                String moves = "";
                for(int i=0;i<details.getMoves().size();i++) {
                    moves = String.format("%s%s, ", type, ((Move_)((Move)details.getMoves().get(i)).getMove()).getName());
                }

                binding.moves.setText(moves);
//                binding.actors.setText(details.getActors());
//                binding.genre.setText(details.getGenre());
//                binding.director.setText(details.getDirector());
//                binding.plot.setText(details.getPlot());
                // binding.language.setText(details.getLanguage());
                requestManager.load(details.getSprites().getFrontDefault()).into(binding.imagePoster);
                setProgressBar(false);
            }else{
                if (!Constants.checkInternetConnection(this)) {
                    Snackbar.make(findViewById(android.R.id.content), Constants.CHECK_NETWORK_ERROR, Snackbar.LENGTH_SHORT)
                            .show();
                }
            }
        });
    }

    private void setProgressBar(boolean flag) {
        if (flag) {
            binding.mainLayout.setVisibility(View.GONE);
            binding.progressBar.setVisibility(View.VISIBLE);
        }else{
            binding.mainLayout.setVisibility(View.VISIBLE);
            binding.progressBar.setVisibility(View.GONE);
        }
    }
}