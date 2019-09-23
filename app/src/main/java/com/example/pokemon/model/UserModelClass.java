package com.example.pokemon.model;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bumptech.glide.Glide;

public class UserModelClass {

    private String name;
    private String imageUrl;
    private String url;


    public UserModelClass(String name,String imageUrl,String url) {
        this.name = name;
        this.url = url;
        this.imageUrl = imageUrl;
    }

    public static DiffUtil.ItemCallback<UserModelClass> DIFF_CALLBACK = new DiffUtil.ItemCallback<UserModelClass>() {
        @Override
        public boolean areItemsTheSame(@NonNull UserModelClass oldItem, @NonNull UserModelClass newItem) {
            return oldItem.name.equals(newItem.name);
        }

        @Override
        public boolean areContentsTheSame(@NonNull UserModelClass oldItem, @NonNull UserModelClass newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        UserModelClass article = (UserModelClass) obj;
        return article.name.equals(this.name);
    }


    public String getImageUrl() {
        return imageUrl;
    }
    public String getName() {
        return name;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @BindingAdapter("profileImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .into(view);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
