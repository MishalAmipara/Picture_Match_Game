package com.example.picture_match_puzzel.Adapters;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.picture_match_puzzel.Activities.level_play_activity;
import com.example.picture_match_puzzel.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class play_adapter extends BaseAdapter
{
    level_play_activity level_play_activity;
    List<String> imagearr = new ArrayList<>();
    ImageView imageView;

    View view1;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        view= LayoutInflater.from(level_play_activity).inflate(R.layout.play_iteam_layout,viewGroup,false);
        imageView=view.findViewById(R.id.imageview_play);
        view1=view.findViewById(R.id.view_play);
        InputStream inputStream=null;
        try {
            inputStream=level_play_activity.getAssets().open(""+ imagearr.get(i));
            Drawable drawable =Drawable.createFromStream(inputStream,null);
            imageView.setImageDrawable(drawable);
            inputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        view1.setVisibility(View.GONE);
        return view;
    }
}
