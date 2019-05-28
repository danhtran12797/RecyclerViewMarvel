package com.example.mypc.recyclerviewmarvel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView textView=findViewById(R.id.txtHeadDetail);
        ImageView imageView=findViewById(R.id.imgMarvelDetail);

        Intent intent=getIntent();
        Marvel marvel= (Marvel) intent.getSerializableExtra("MARVEL");

        textView.setText(marvel.getName());
        Picasso.get().load(marvel.getImage()).into(imageView);
    }
}
