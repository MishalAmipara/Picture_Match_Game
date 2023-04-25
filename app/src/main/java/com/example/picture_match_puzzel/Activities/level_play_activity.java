package com.example.picture_match_puzzel.Activities;



import androidx.appcompat.app.AppCompatActivity;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ProgressBar;
;

import com.example.picture_match_puzzel.Adapters.play_adapter;
import com.example.picture_match_puzzel.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class level_play_activity extends AppCompatActivity {
    int level;
    String status;
    ArrayList<String>imagearr=new ArrayList<>();
    List<String>arraylist=new ArrayList<>();
    ProgressBar progressBar;
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_play);
        level=getIntent().getIntExtra("level",0);
        status=getIntent().getStringExtra("status");
        progressBar=findViewById(R.id.progress);
        gridView=findViewById(R.id.grid_view_play);


        String [] images =new String[0];
        try {
            images=getAssets().list("/images");
            imagearr=new ArrayList<>(Arrays.asList(images));
        } catch (IOException e) {
            e.printStackTrace();
        }
        arraylist= (List<String>) imagearr.clone();
        InputStream inputStream=null;
        try {
            inputStream=getAssets().open("/images"+ imagearr.get(level ));
            Drawable drawable =Drawable.createFromStream(inputStream,null);
            inputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        play_adapter playAdapter=new play_adapter(level_play_activity.this,imagearr);
    }

}
