package com.taak.pro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.taak.pro.POJO.Content;
import com.taak.pro.POJO.Watchnextmovies;
import com.taak.pro.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterWatchnext extends RecyclerView.Adapter<AdapterWatchnext.ViewHolder> {

    Context context;
    List<Watchnextmovies> watchnextmovies;
    public AdapterWatchnext(Context context,List<Watchnextmovies> watchnextmovies){
        this.context = context;
        this.watchnextmovies = watchnextmovies;

    }

    @NonNull
    @Override
    public AdapterWatchnext.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_movie_watchnext, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterWatchnext.ViewHolder holder, int position) {
        Picasso.get().load("https://taak.digital"+watchnextmovies.get(position).v_poster).into(holder.img);

       final Watchnextmovies temp = watchnextmovies.get(position);

       if(watchnextmovies.get(position).url.contains("series")) {
           holder.img.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(context, MainActivity6.class);
                   Content content = new Content();
                   content.name = temp.name;
                   content.age_rating = temp.age_rating;
                   content.url = temp.url;
                   content.poster = temp.poster;
                   content.v_poster = temp.v_poster;
                   intent.putExtra("content", new Gson().toJson(content));
                   context.startActivity(intent);
                   ((Activity) context).finish();

               }
           });
       }else if(watchnextmovies.get(position).url.contains("movies")){
           holder.img.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent intent = new Intent(context, Movies.class);
                   Content content = new Content();
                   content.name = temp.name;
                   content.age_rating = temp.age_rating;
                   content.url = temp.url;
                   content.poster = temp.poster;
                   content.v_poster = temp.v_poster;
                   intent.putExtra("content", new Gson().toJson(content));
                   context.startActivity(intent);
                   ((Activity) context).finish();

               }
           });
       }


    }

    @Override
    public int getItemCount() {
        return watchnextmovies.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.watchnextsss);
        }
    }
}
