package com.example.picture_match_puzzel.Adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.picture_match_puzzel.Activities.level_play_activity;
import com.example.picture_match_puzzel.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class lavel_adapter extends BaseAdapter
{
    Context context;
    Button button,go;

    SharedPreferences preferences;
    public  static  int level[]={1,2,3,4,5,6,7,8,9,10};
    public static  String status=null;
    public lavel_adapter(Context context, SharedPreferences preferences) {
        this.context=context;
        this.preferences=context.getSharedPreferences("pre",Context.MODE_PRIVATE);
    }

    @Override
    public int getCount() {
        return 10;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(context).inflate(R.layout.level_iteam_view,viewGroup,false);
        button=view.findViewById(R.id.level_button);
        button.setText("Level "+level[i]);
        status=preferences.getString("status","default");
        String levels=preferences.getString("levels"+i,"default");
        int lastlevel=preferences.getInt("lastlevel",-1);
        if (levels.equals("Win"))
        {
            button.setVisibility(View.VISIBLE);
        }
        if ( i==lastlevel+1)
        {
            button.setVisibility(View.VISIBLE);
        }
        if (levels.equals("Win") || i==lastlevel+1) {
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("Clicked");

                    Intent intent = new Intent(context, level_play_activity.class);
                    intent.putExtra("level", level[i]);
                    intent.putExtra("status", status);
                    context.startActivity(intent);
                }
            });
        }
        return view;
    }
}
