package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.nio.file.attribute.PosixFileAttributes;
import java.util.ArrayList;

public class NewsRvAdapter extends RecyclerView.Adapter<NewsRvAdapter.ViewHolder> {
    private ArrayList<Articles> articlesArrayList;

    public NewsRvAdapter(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    private Context context;
    @NonNull
    @Override
    public NewsRvAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_rvitem,parent,false);
        return new NewsRvAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRvAdapter.ViewHolder holder, int position) {
        Articles articles = articlesArrayList.get(position);
        holder.newsHeadText.setText(articles.getTitle());
        holder.newsSubheading.setText(articles.getDescription());
        Picasso.get().load(articles.getUrltoImage()).into(holder.RVImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,NewsDetail.class);
                i.putExtra("title",articles.getTitle());
                i.putExtra("content",articles.getContent());
                i.putExtra("desc",articles.getDescription());
                i.putExtra("image",articles.getUrltoImage());
                i.putExtra("url",articles.getUrl());
                context.startActivity(i);

            }
        }
        );



    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView newsHeadText,newsSubheading;
        private ImageView RVImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsHeadText = itemView.findViewById(R.id.newsHeading);
            newsSubheading = itemView.findViewById(R.id.subheading);
            RVImage = itemView.findViewById(R.id.RVImageNews);
        }
    }
}
