package com.example.project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.POJO.Content;
import com.example.project2.POJO.Upcoming;
import com.example.project2.POJO.Watchnextmovies;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterUpcoming extends RecyclerView.Adapter<AdapterUpcoming.ViewHolder> {
    Context context;
    List<Upcoming> upcomings1;

    public AdapterUpcoming(Context context,List<Upcoming> upcomings1){
        this.context = context;
        this.upcomings1 =upcomings1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_upcomming_next, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load("https://katto.in"+upcomings1.get(position).poster).into(holder.img1);
        Picasso.get().load("https://katto.in"+upcomings1.get(position).poster).into(holder.img2);
    }

    @Override
    public int getItemCount() {
        return upcomings1.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img1,img2;
        TextView date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img1=itemView.findViewById(R.id.img11);
            img2=itemView.findViewById(R.id.img22);
            date=itemView.findViewById(R.id.text444);
        }
    }
}
