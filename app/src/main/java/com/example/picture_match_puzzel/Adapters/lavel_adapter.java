package com.example.picture_match_puzzel.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.picture_match_puzzel.Activities.level_play_activity;
import com.example.picture_match_puzzel.R;

public class lavel_adapter extends BaseAdapter
{
    Context context;
    Button button;
    String status;
    SharedPreferences preferences;
    int level[]={1,2,3,4,5,6,7,8,9,10};
    public lavel_adapter(Context context, SharedPreferences preferences) {
        this.preferences= context.getSharedPreferences("pre",Context.MODE_PRIVATE);
        this.context=context;
    }

    @Override
    public int getCount() {
        return level.length;
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
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Clicked");
                Intent intent = new Intent(context, level_play_activity.class);
                intent.putExtra("status",status);
                intent.putExtra("level",level[i]);
                context.startActivity(intent);
            }
        });
        return view;
    }
}
