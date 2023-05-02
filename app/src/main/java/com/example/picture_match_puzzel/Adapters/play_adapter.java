package com.example.picture_match_puzzel.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.picture_match_puzzel.Activities.No_Time_Activity;
import com.example.picture_match_puzzel.Activities.hard_level_activity;
import com.example.picture_match_puzzel.Activities.level_play_activity;
import com.example.picture_match_puzzel.Activities.normal_activity;
import com.example.picture_match_puzzel.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class play_adapter extends BaseAdapter
{
    level_play_activity level_play_activity;
    List<String> imagearr = new ArrayList<>();
    ProgressBar progressBar;
    View firstview;

    int click = 1,pos1=0,pos2=0,count=0;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
   TextView toolbar;
    Runnable runnable;
    public play_adapter(level_play_activity level_play_activity, SharedPreferences preferences, List<String> arraylist, ProgressBar progressBar, TextView toolbar)
    {
        this.level_play_activity=level_play_activity;
        this.imagearr=arraylist;
        this.progressBar=progressBar;
        this.preferences=level_play_activity.getSharedPreferences("pre", Context.MODE_PRIVATE);
        editor=preferences.edit();
        this.toolbar=toolbar;
    }

    @Override
    public int getCount() {
        return imagearr.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        //int click = 1,pos1=0,pos2=0;
        view= LayoutInflater.from(level_play_activity).inflate(R.layout.play_iteam_layout,viewGroup,false);
        View view1;

        ImageView imageView;
        imageView=view.findViewById(R.id.imageview_play);
        view1=view.findViewById(R.id.view_play);
        RelativeLayout relativeLayout = view.findViewById(R.id.relativeLayout);
        InputStream inputStream=null;
        try {
            inputStream=level_play_activity.getAssets().open(""+ imagearr.get(i));
            Drawable drawable =Drawable.createFromStream(inputStream,null);
            imageView.setImageDrawable(drawable);
            inputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        progressBar.setMax(5);
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                int interval= (int) (l/1000);
                progressBar.setProgress(interval);
                toolbar.setText("Time: "+interval+"/0");
            }

            @Override
            public void onFinish() {
                startGame(relativeLayout,i,view1);
                view1.setVisibility(View.VISIBLE);
            }

        }.start();





//        view1.setVisibility(View.GONE);
        return view;
    }

    private void startGame(RelativeLayout relativeLayout, int i, View view1)
    {

        int interval = 5000; // 5 Second
        Handler handler = new Handler();

        relativeLayout.setOnClickListener(v ->
        {
            if (click == 1)
            {
                view1.setVisibility(View.INVISIBLE);
                pos1 = i;
                firstview = view1;
                click = 3;

                runnable = new Runnable() {
                    @Override
                    public void run() {
                        click=2;
                    }
                };
                //handler.postAtTime(runnable.get(), System.currentTimeMillis() + interval);
                handler.postDelayed(runnable, 100);
                System.out.println("first click");
            }
            if (click == 2) {
                view1.setVisibility(View.INVISIBLE);
                pos2 = i;
                click = 3;
                System.out.println("second click");
                if (imagearr.get(pos1).equals(imagearr.get(pos2))) {
                    System.out.println("match");
                    runnable = new Runnable() {
                        public void run() {
                            click = 1;
                            count++;
                            if (count==6 ||count==8|| count==10) {
                                editor.putInt("count", count);
                                editor.putString("levels"+i,"Win");
                                editor.putInt("lastlevel",i);

                                editor.commit();
                                AlertDialog.Builder builder=new AlertDialog.Builder(level_play_activity);
                                builder.setTitle("Congrats");
                                builder.setMessage("YOU WON THIS LEVEL");
                                String staus=preferences.getString("status","default");

                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (staus.equals("notime")) {
                                            Intent intent = new Intent(level_play_activity, No_Time_Activity.class);
                                            intent.putExtra("level",i+1);
                                            level_play_activity.startActivity(intent);

                                        }
                                        if (staus.equals("hard")) {
                                            Intent intent = new Intent(level_play_activity, hard_level_activity.class);
                                            intent.putExtra("level",i+1);
                                           level_play_activity. startActivity(intent);

                                        }
                                        if (staus.equals("Normal")) {
                                            Intent intent = new Intent(level_play_activity, normal_activity.class);
                                            intent.putExtra("level",i+1);
                                            level_play_activity.startActivity(intent);

                                        }
                                       level_play_activity. finish();

                                    }
                                });
                                builder.show();
                            }
                            else
                            {

                                editor.putInt("lastlevel",i);
                                editor.commit();
                            }
                        }
                    };
                    //handler.postAtTime(runnable, System.currentTimeMillis() + interval);
                    handler.postDelayed(runnable, 100);
                }
                else {
                    System.out.println("not match");
                    runnable = new Runnable() {
                        public void run() {
                            view1.setVisibility(View.VISIBLE);
                            firstview.setVisibility(View.VISIBLE);
                            click = 1;
                        }
                    };
                    //handler.postAtTime(runnable, System.currentTimeMillis() + interval);
                    handler.postDelayed(runnable, 100);
                }
            }
        });
    }

}
