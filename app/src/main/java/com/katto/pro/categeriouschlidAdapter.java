package com.katto.pro;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.katto.pro.POJO.Content;
import com.katto.pro.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class categeriouschlidAdapter extends RecyclerView.Adapter<categeriouschlidAdapter.ViewHolder> {
    Context context;
    List<Content> memberList;

    public categeriouschlidAdapter(Context context, List<Content> members){
        this.context=context;
        this.memberList=members;
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

//        Picasso.get().load("https://katto.in"+seriesList.get(position).v_poster).into( h.img); //for series

        final Content temp=memberList.get(position);

         if(memberList.get(position).url.contains("movie")){
             h.img.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent =new Intent(context,Movies.class);
                     intent.putExtra("content",new Gson().toJson(temp) ); //for series
                     context.startActivity(intent);
                 }
             });
         }else if(memberList.get(position).url.contains("series")){
             h.img.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent =new Intent(context,MainActivity6.class);
                     intent.putExtra("content", new Gson().toJson(temp));
                     context.startActivity(intent);

                 }
             });

         }

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
