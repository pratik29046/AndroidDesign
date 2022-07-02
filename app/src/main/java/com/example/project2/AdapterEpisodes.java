package com.example.project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.POJO.Episode;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterEpisodes extends RecyclerView.Adapter<AdapterEpisodes.ViewHolder>{
    private List<Episode> userList2;
    Context context1;

    public AdapterEpisodes(List<Episode> userList2,Context context1){
        this.userList2=userList2;
        this.context1=context1;
    }


    @NonNull
    @Override
    public AdapterEpisodes.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main8, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterEpisodes.ViewHolder holder, int position) {
        Picasso.get().load("https://katto.in"+userList2.get(position).poster).into( holder.vs);
        holder.n.setText(userList2.get(position).name);
        holder.d.setText(userList2.get(position).content_link);
        holder.no.setText(userList2.get(position).number);
        holder.time.setText(userList2.get(position).number);

    }

    @Override
    public int getItemCount() {
        return userList2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView vs;
        private TextView n;
        private  TextView d;
        private TextView no,time;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vs=itemView.findViewById(R.id.video1);
            n=itemView.findViewById(R.id.name);
            d=itemView.findViewById(R.id.desc);
            no=itemView.findViewById(R.id.number);
            time=itemView.findViewById(R.id.time);
        }
    }
}
