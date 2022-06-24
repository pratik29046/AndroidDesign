package com.example.project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.POJO.Watchnextmovies;
import com.example.project2.POJO.Watchnextseries;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterWachnextSeries extends RecyclerView.Adapter<AdapterWachnextSeries.ViewHolder> {
    Context context;
    List<Watchnextseries> watchnextseries;
    public AdapterWachnextSeries(Context context,List<Watchnextseries> watchnextseries){
        this.context = context;
        this.watchnextseries = watchnextseries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_series_watchnext, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load("https://katto.in"+watchnextseries.get(position).v_poster).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return watchnextseries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.serieswatchnexts);
        }
    }
}
