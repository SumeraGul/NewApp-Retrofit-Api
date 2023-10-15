package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetail extends AppCompatActivity {
    private TextView txtTitle,txtdesc,txtcontent;
    private ImageView Image;
    private Button btnfulltext;
    String title,content,desc,image,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        txtTitle = findViewById(R.id.TvIdNews);
        txtdesc = findViewById(R.id.SubDescription);
        txtcontent = findViewById(R.id.contentText);
        Image = findViewById(R.id.IVNews);
        btnfulltext = findViewById(R.id.ReadFullText);

        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        desc = getIntent().getStringExtra("desc");
        image = getIntent().getStringExtra("image");
        url = getIntent().getStringExtra("url");
        //set texts
        txtTitle.setText(title);
        txtdesc.setText(desc);
        txtcontent.setText(content);
        Picasso.get().load(image).into(Image);
        btnfulltext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

    }
}