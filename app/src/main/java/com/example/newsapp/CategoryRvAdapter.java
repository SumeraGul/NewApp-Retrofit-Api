package com.example.newsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryRvAdapter extends RecyclerView.Adapter<CategoryRvAdapter.viewHolder> {
    ArrayList<CategoryRVModal> categoryRVModals;
    private Context context;
    private CategoryClickInterface categoryClickInterface;

    public CategoryRvAdapter(ArrayList<CategoryRVModal> categoryRVModals, Context context, CategoryClickInterface categoryClickInterface) {
        this.categoryRVModals = categoryRVModals;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }








    @NonNull
    @Override
    public CategoryRvAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.catogery_rv,parent,false);
        return new CategoryRvAdapter.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryRvAdapter.viewHolder holder, @SuppressLint("RecyclerView") int position) {
        CategoryRVModal categoryRVModal = categoryRVModals.get(position);
        holder.txt.setText(categoryRVModal.getCategory());
        Picasso.get().load(categoryRVModal.getCategoryImageUrl()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryClickInterface.onCategoryClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoryRVModals.size();
    }
    public interface CategoryClickInterface{
        void onCategoryClick(int position);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private  TextView txt;
        private ImageView imageView;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.txtCategory);
            imageView = itemView.findViewById(R.id.RVImageCategory);
        }
    }
}
