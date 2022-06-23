package com.example.project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.POJO.Category;
import com.example.project2.POJO.Watchnextmovies;
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
        Picasso.get().load("https://katto.in"+watchnextmovies.get(position).v_poster).into(holder.img);
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
