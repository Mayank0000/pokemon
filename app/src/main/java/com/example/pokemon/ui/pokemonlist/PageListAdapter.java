package com.example.pokemon.ui.pokemonlist;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemon.R;
import com.example.pokemon.databinding.RowLayoutBinding;
import com.example.pokemon.model.UserModelClass;

public class PageListAdapter extends PagedListAdapter<UserModelClass, RecyclerView.ViewHolder>{

    private onRowClick onClickRowEvent;


    public PageListAdapter(onRowClick onClickRowEvent) {
        super(UserModelClass.DIFF_CALLBACK);
        this.onClickRowEvent = onClickRowEvent;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            RowLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                    R.layout.row_layout, parent, false);
            return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder1, int position) {
            MyViewHolder holder = (MyViewHolder) holder1;
            holder.binding.setModel(getItem(position));
            holder.itemView.setOnClickListener(view ->
                    onClickRowEvent.onItemClick(holder.binding.getModel().getUrl()));
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        RowLayoutBinding binding;
        MyViewHolder(RowLayoutBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }
    }

}
