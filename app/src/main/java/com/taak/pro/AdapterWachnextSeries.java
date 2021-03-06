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
import com.taak.pro.POJO.Watchnextseries;
import com.taak.pro.R;
import com.google.gson.Gson;
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
        Picasso.get().load("https://taak.digital"+watchnextseries.get(position).v_poster).into(holder.img);


        final Watchnextseries temp=watchnextseries.get(position);

//        holder.img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent =new Intent(context,MainActivity6.class);
//                Content content=new Content();
//                content.name=temp.name;
//                content.url=temp.url;
//                content.poster=temp.poster;
//                content.v_poster=temp.v_poster;
//                content.age_rating= temp.age_rating;
//                intent.putExtra("content", new Gson().toJson(content));
//                context.startActivity(intent);
//                ((Activity)context).finish(); //back
//            }
//        });

        if(watchnextseries.get(position).url.contains("series")) {
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
        }else if(watchnextseries.get(position).url.contains("movies")){
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
