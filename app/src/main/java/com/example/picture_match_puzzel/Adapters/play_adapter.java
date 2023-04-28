package com.example.picture_match_puzzel.Adapters;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.picture_match_puzzel.Activities.level_play_activity;
import com.example.picture_match_puzzel.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class play_adapter extends BaseAdapter
{
    level_play_activity level_play_activity;
    List<String> imagearr = new ArrayList<>();
    View firstview;
    int click = 1,pos1=0,pos2=0;

    Runnable runnable;
    public play_adapter(level_play_activity level_play_activity, List<String> arraylist)
    {
        this.level_play_activity=level_play_activity;
        this.imagearr=arraylist;

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
        int interval = 5000; // 5 Second
        Handler handler = new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                view1.setVisibility(View.VISIBLE);
            }
        };
            handler.postAtTime(runnable, System.currentTimeMillis() + interval);
            handler.postDelayed(runnable, interval);
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

//        view1.setVisibility(View.GONE);
        return view;
    }
}
