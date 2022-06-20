package com.example.project2;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.POJO.Content;
import com.example.project2.POJO.HomePOJO;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class categeriouschlidAdapter extends RecyclerView.Adapter<categeriouschlidAdapter.ViewHolder> {
    Context context;
    List<Content> memberList;

    public categeriouschlidAdapter(List<Content> members){
       memberList=members;
    }

    @NonNull
    @NotNull
    @Override
    public categeriouschlidAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chlidcategoies_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull final categeriouschlidAdapter.ViewHolder h, int position) {


        Picasso.get().load("https://katto.in"+memberList.get(position).v_poster).into( h.img);
//        Log.d("TAG", "onBindViewHolder: "+"https://katto.in"+memberList.get(position).poster);
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
        }

    }
}
