package com.example.project2;

import android.content.Context;
import android.content.Intent;
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
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
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
        holder.age.setText(upcomings1.get(position).age_rating);
        holder.date.setText(upcomings1.get(position).release_date);
        holder.lang.setText(upcomings1.get(position).language);
        holder.genres.setText(upcomings1.get(position).genres);
        holder.description.setText(upcomings1.get(position).description);
        holder.starring.setText(upcomings1.get(position).starring);
        holder.directors.setText(upcomings1.get(position).directors);

        final Upcoming temp= upcomings1.get(position);

        holder.play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(context,MainPlayerActivity.class);
                intent.putExtra("link",temp.trailer_link);
                intent.putExtra("name",temp.name);
                context.startActivity(intent);


//                String url = "https://multiplatform-f.akamaihd.net/i/multi/will/bunny/big_buck_bunny_,640x360_400,640x360_700,640x360_1000,950x540_1500,.f4v.csmil/master.m3u8";
               /* holder.play.setVisibility(View.GONE);
                holder.img1.setVisibility(View.GONE);
                holder.playerView.setVisibility(View.VISIBLE);

                SimpleExoPlayer simpleExoPlayer = new SimpleExoPlayer.Builder(context).build();
                holder.playerView = holder.playerView;
                holder.playerView.setPlayer(simpleExoPlayer);
                MediaItem mediaItem = MediaItem.fromUri(url);
                simpleExoPlayer.addMediaItem(mediaItem);
                simpleExoPlayer.prepare();
                simpleExoPlayer.play();*/

               /* if (simpleExoPlayer != null) {
                    holder.play.setVisibility(View.GONE);
                    holder.img1.setVisibility(View.GONE);
                    holder.playerView.setVisibility(View.VISIBLE);

                }else if(simpleExoPlayer == null){
                    holder.play.setVisibility(View.VISIBLE);
                    holder.img1.setVisibility(View.VISIBLE);
                    simpleExoPlayer.stop();
                    holder.playerView.setVisibility(View.GONE);

                }*/

            }
        });

    }


    @Override
    public int getItemCount() {
        return upcomings1.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        PlayerView playerView;
        ImageView img1,img2,play;
        TextView date,age,lang,genres,description,starring,directors;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img1=itemView.findViewById(R.id.img11);
            img2=itemView.findViewById(R.id.img22);
            date=itemView.findViewById(R.id.text444);
            age=itemView.findViewById(R.id.text5);
            lang=itemView.findViewById(R.id.text20);
            genres=itemView.findViewById(R.id.text22);
            description=itemView.findViewById(R.id.text2);
            starring=itemView.findViewById(R.id.text14);
            directors=itemView.findViewById(R.id.text6);

            play=itemView.findViewById(R.id.img34);
            playerView=itemView.findViewById(R.id.exoplayer1);


        }
    }


    



}
