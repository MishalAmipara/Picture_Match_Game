package com.example.picture_match_puzzel.Activities;



import androidx.appcompat.app.AppCompatActivity;


import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toolbar;
;

import com.example.picture_match_puzzel.Adapters.play_adapter;
import com.example.picture_match_puzzel.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class level_play_activity extends AppCompatActivity {
    int level;
    String status;
    ArrayList<String>imagearr=new ArrayList<>();
    List<String>arraylist=new ArrayList<>();
    ProgressBar progressBar;
    GridView gridView;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
     int numimage,collum;
     TextView textView;
     Toolbar toolbar;
     Button back,go;
    TextView textView1,textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_play);
        level=getIntent().getIntExtra("level",0);
        status=getIntent().getStringExtra("status");
        progressBar=findViewById(R.id.progress);
        gridView=findViewById(R.id.grid_view_play);
        preferences=getSharedPreferences("pre",MODE_PRIVATE);
        editor=preferences.edit();
        toolbar=findViewById(R.id.Toolbar);
        textView=findViewById(R.id.Time_text_view);
        back=findViewById(R.id.back_button);



        setActionBar(toolbar);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (status.equals("notime")) {
                    Intent intent = new Intent(level_play_activity.this, No_Time_Activity.class);
                    startActivity(intent);

                }
                if (status.equals("hard")) {
                    Intent intent = new Intent(level_play_activity.this, hard_level_activity.class);

                    startActivity(intent);

                }
                if (status.equals("Normal")) {
                    Intent intent = new Intent(level_play_activity.this, normal_activity.class);

                    startActivity(intent);

                }
                finish();

            }
        });
        if (level<=3)
        {
            numimage = 6;
            collum = 3;
        }else if(level<=6)
        {
            numimage=8;
            collum=4;
        }else if(level<=10)
        {
            numimage=10;
            collum=4;
        }


        String [] images =new String[0];
        try {
            images=getAssets().list("");
            imagearr=new ArrayList<String>(Arrays.asList(images));
        } catch (IOException e) {
            e.printStackTrace();
        }



        arraylist=imagearr.subList(0,numimage);

        arraylist.addAll(arraylist);
        Collections.shuffle(arraylist);
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.time_show_diloge);
        textView1=dialog.findViewById(R.id.time_give_txt);
        textView2=dialog.findViewById(R.id.tips_txt);
        go=dialog.findViewById(R.id.go_button);
        textView1.setText("Time : "+status);
        textView2.setText("You Have 5 Second To Memorize "+"\n"+"All Images");
        dialog.show();

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play_adapter playAdapter=new play_adapter(level_play_activity.this,arraylist,progressBar,textView);
                gridView.setNumColumns(collum);
                gridView.setAdapter(playAdapter);
                dialog.cancel();
            }
        });











    }

}
