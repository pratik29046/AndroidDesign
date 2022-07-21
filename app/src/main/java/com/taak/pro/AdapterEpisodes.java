package com.taak.pro;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.taak.pro.POJO.Episode;
import com.taak.pro.R;
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
        Picasso.get().load("https://taak.digital"+userList2.get(position).poster).into( holder.vs);
        holder.n.setText(userList2.get(position).name);
        holder.no.setText(userList2.get(position).number);
        holder.time.setText(userList2.get(position).duration);

        if(userList2.get(position).free==true) {
            holder.free.setVisibility(View.GONE);

        }
            final Episode temp = userList2.get(position);

            holder.vs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context1, MainPlayerActivity.class);
                    intent.putExtra("link", temp.content_link);
                    intent.putExtra("name", temp.name);
                    context1.startActivity(intent);
                }
            });


    }

    @Override
    public int getItemCount() {
        return userList2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView vs;
        private TextView n;
        private TextView no,time,free;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vs=itemView.findViewById(R.id.video1);
            n=itemView.findViewById(R.id.name);
            no=itemView.findViewById(R.id.number);
            time=itemView.findViewById(R.id.time);
            free=itemView.findViewById(R.id.free);
        }
    }
}
