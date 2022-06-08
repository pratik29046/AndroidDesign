package com.example.project2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapters extends RecyclerView.Adapter<Adapters.ViewHolder> {
    private List<ModelClass2> userList2;
    public Adapters(List<ModelClass2> userList2){
        this.userList2=userList2;
    }
    @NonNull
    @Override
    public Adapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main8, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapters.ViewHolder holder, int position) {
        String vid=userList2.get(position).getVid();
        String name=userList2.get(position).getName();
        int img =userList2.get(position).getImg();
        String desc=userList2.get(position).getDesc();
        holder.setData(vid,name,img,desc);

    }

    @Override
    public int getItemCount() {
        return userList2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private VideoView v;
        private TextView n;
        private  ImageView i;
        private  TextView d;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            v=itemView.findViewById(R.id.video);
            n=itemView.findViewById(R.id.name);
            i=itemView.findViewById(R.id.img);
            d=itemView.findViewById(R.id.desc);

        }

        public void setData(String vid, String name, int img, String desc) {
            v.setVideoPath(vid);
            n.setText(name);
            i.setImageResource(img);
            d.setText(desc);
        }
    }
}