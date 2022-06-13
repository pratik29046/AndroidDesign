package com.example.project2;

import android.content.Context;
import android.content.Intent;
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
    Context context1;

    public Adapters(List<ModelClass2> userList2,Context context1){
        this.userList2=userList2;
        this.context1=context1;
    }
    @NonNull
    @Override
    public Adapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main8, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapters.ViewHolder holder, int position) {
        final  ModelClass2 temp1=userList2.get(position);

        String vid=userList2.get(position).getVid();
        String name=userList2.get(position).getName();
        int img =userList2.get(position).getImg();
        String desc=userList2.get(position).getDesc();
        holder.setData(vid,name,img,desc);

        holder.n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context1,MainActivity7.class);
                intent.putExtra("vid",temp1.getVid());
//                intent.putExtra("name",temp.getVid());
//                intent.putExtra("img",temp.getVid());
//                intent.putExtra("desc",temp.getVid());
                context1.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userList2.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private VideoView vs;
        private TextView n;
        private  ImageView i;
        private  TextView d;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vs=itemView.findViewById(R.id.video1);
            n=itemView.findViewById(R.id.name);
            i=itemView.findViewById(R.id.img);
            d=itemView.findViewById(R.id.desc);
        }

        public void setData(String vid, String name, int img, String desc) {
            vs.setVideoPath(vid);
            n.setText(name);
            i.setImageResource(img);
            d.setText(desc);
        }
    }
}
