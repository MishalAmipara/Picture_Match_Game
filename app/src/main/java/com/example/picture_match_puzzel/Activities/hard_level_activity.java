package com.example.picture_match_puzzel.Activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.example.picture_match_puzzel.Adapters.lavel_adapter;
import com.example.picture_match_puzzel.R;

public class hard_level_activity extends AppCompatActivity {
    GridView gridView;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_level);
        gridView=findViewById(R.id.hard_grid_view);
        button=findViewById(R.id.warning_button3);
        lavel_adapter lavelAdapter=new lavel_adapter(hard_level_activity.this);
        gridView.setAdapter(lavelAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(hard_level_activity.this,level_play_activity.class);
                startActivity(intent);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(hard_level_activity.this);
                builder.setTitle("How To Play match 1");
                builder.setMessage("Tap on a square to turn it over and see the"+"\n"+" picture it hides."
                        +"\n \n"+"Tap ao another square to turn it over too."
                        +"\n \n"+"if the picture match,they'll stay facing up, if not,"+"\n"+"both will turn over again."
                        +"\n \n"+"Find all couples.");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();
            }
        });

    }
}