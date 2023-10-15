package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//c75fe20eb140427bb2bf7837361c52f0
public class MainActivity extends AppCompatActivity {
    private RecyclerView categoryRv, NewsRv;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVModal> categoryRVModalArrayList;
    private ProgressBar progressBar;
    private NewsRvAdapter newsRvAdapter;
    private CategoryRvAdapter categoryRvAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categoryRv = findViewById(R.id.Rvcategory);
        NewsRv= findViewById(R.id.RvNews);
        progressBar= findViewById(R.id.progresbar);
        articlesArrayList = new ArrayList<>();
        NewsModal newsModal = new NewsModal();
        categoryRVModalArrayList = new ArrayList<>();
        newsRvAdapter = new NewsRvAdapter(articlesArrayList,this);
        categoryRvAdapter = new CategoryRvAdapter(categoryRVModalArrayList,this,this::onCategoryClick);
        NewsRv.setLayoutManager(new LinearLayoutManager(this));
        NewsRv.setAdapter(newsRvAdapter);
        categoryRv.setAdapter(categoryRvAdapter);
        getCtaegories();
        getNews("All");
        newsRvAdapter.notifyDataSetChanged();
    }
    private void getCtaegories(){
        categoryRVModalArrayList.add(new CategoryRVModal("All","https://images.unsplash.com/photo-1635156219587-879ded59e273?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=395&q=80"));
        categoryRVModalArrayList.add(new CategoryRVModal("Technology","https://images.unsplash.com/photo-1488590528505-98d2b5aba04b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=2070&q=80"));
        categoryRVModalArrayList.add(new CategoryRVModal("Science","https://images.unsplash.com/photo-1518152006812-edab29b069ac?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        categoryRVModalArrayList.add(new CategoryRVModal("Bussiness","https://images.unsplash.com/photo-1612550761236-e813928f7271?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        categoryRVModalArrayList.add(new CategoryRVModal("Sports","https://images.unsplash.com/photo-1517649763962-0c623066013b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80"));
        categoryRVModalArrayList.add(new CategoryRVModal("Health","https://images.unsplash.com/photo-1511688878353-3a2f5be94cd7?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=387&q=80"));
        categoryRVModalArrayList.add(new CategoryRVModal("Entertainment","https://images.unsplash.com/photo-1586899028174-e7098604235b?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=871&q=80"));
        categoryRVModalArrayList.add(new CategoryRVModal("General","https://images.unsplash.com/photo-1493612276216-ee3925520721?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=464&q=80"));
        categoryRvAdapter.notifyDataSetChanged();
    }
    private void getNews(String category){
        progressBar.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryUrl =" https://newsapi.org/v2/top-headlines?country=in&category"+category+"apiKey=c75fe20eb140427bb2bf7837361c52f0";
        String url =" https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=c75fe20eb140427bb2bf7837361c52f0";
        String Base_url="https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<NewsModal> call;
        if(category.equals("All")){
            call = retrofitAPI.getAllNews(url);
        }
        else{
            call = retrofitAPI.getNewsByCategory(categoryUrl);

        }
        call.enqueue(new Callback<NewsModal>() {


            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal = response.body();
                progressBar.setVisibility(View.GONE);
                ArrayList<Articles> articles = newsModal.getArticles();
                 for( int i=0;i < articles.size(); i++) {
                        articlesArrayList.add(new Articles(articles.get(i).getTitle(),
                                articles.get(i).getDescription(),
                                articles.get(i).getUrltoImage(),
                                articles.get(i).getUrl(),
                                articles.get(i).getContent()));
                    }


                newsRvAdapter.notifyDataSetChanged();


        }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(MainActivity.this,"failed to get news",Toast.LENGTH_SHORT).show();

            }
        });

        }



    private void onCategoryClick(int i) {
        String Category = categoryRVModalArrayList.get(i).getCategory();
        getNews(Category);
    }
}