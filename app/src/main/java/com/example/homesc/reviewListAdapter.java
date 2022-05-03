package com.example.homesc;

import android.content.Context;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class reviewListAdapter extends ArrayAdapter<reviewItem>{
    Context context;
    int res;

    public reviewListAdapter(Context context, int resource, ArrayList<reviewItem> list)
    {
        super(context,resource,list);
        this.context=context;
        this.res=resource;
    }

    @NonNull
    @Override
    public View getView(int pos, View convView, ViewGroup parent)
    {
        LayoutInflater inflate=LayoutInflater.from(this.context);
        convView=inflate.inflate(res,parent,false);

        reviewItem review=getItem(pos);

        RatingBar rating=(RatingBar) convView.findViewById(R.id.listRating);
        TextView comment=(TextView) convView.findViewById(R.id.listReview);

        rating.setRating(review.getItemRating());
        comment.setText(review.getItemReview());

        return convView;
    }

}
