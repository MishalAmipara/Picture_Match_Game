package com.example.picture_match_puzzel.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import androidx.core.content.ContextCompat;

import com.example.picture_match_puzzel.Activities.No_Time_Activity;
import com.example.picture_match_puzzel.Activities.level_play_activity;
import com.example.picture_match_puzzel.R;

public class lavel_adapter extends BaseAdapter
{
    Context context;
    Button button;
    public lavel_adapter(Context context) {
        this.context=context;
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
        button.setText("Level "+(i+1));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Clicked");
                Intent intent = new Intent(context, level_play_activity.class);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
