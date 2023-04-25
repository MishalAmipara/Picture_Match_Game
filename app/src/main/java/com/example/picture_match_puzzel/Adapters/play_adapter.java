package com.example.picture_match_puzzel.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.picture_match_puzzel.Activities.level_play_activity;
import com.example.picture_match_puzzel.R;

import java.util.ArrayList;

public class play_adapter extends BaseAdapter
{
    level_play_activity level_play_activity;
    ArrayList<String> imagearr=new ArrayList<>();
    ImageView imageView;
    View view1;
    public play_adapter(level_play_activity level_play_activity, ArrayList<String> imagearr)
    {
        this.level_play_activity=level_play_activity;
        this.imagearr=imagearr;

    }

    @Override
    public int getCount() {
        return imagearr.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(level_play_activity).inflate(R.layout.play_iteam_layout,viewGroup,false);
        imageView=view.findViewById(R.id.imageview_play);
        view1=view.findViewById(R.id.view_play);
        imageView.setImageResource(Integer.parseInt(imagearr.get(i)));
        view1.setVisibility(View.GONE);
        return view;
    }
}
