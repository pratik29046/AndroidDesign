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

import com.example.project2.POJO.Episode;
import com.example.project2.POJO.RootSeries;
import com.example.project2.POJO.Series;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapters extends RecyclerView.Adapter<Adapters.ViewHolder> {
    private List<Episode> userList2;
    Context context1;


    public Adapters(List<Episode> userList2,Context context1){
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
        Picasso.get().load("https://katto.in"+userList2.get(position).poster).into( holder.vs);
        holder.n.setText(userList2.get(position).name);
        holder.d.setText(userList2.get(position).content_link);
        holder.no.setText(userList2.get(position).number);
        holder.time.setText(userList2.get(position).number);


//        final  ModelClass2 temp1=userList2.get(position);

        holder.n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context1,MainActivity7.class);
//                intent.putExtra("vid",temp1.getVid());
////                intent.putExtra("name",temp.getVid());
////                intent.putExtra("img",temp.getVid());
////                intent.putExtra("desc",temp.getVid());
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

//        public void setData(int img1, String name, int img, String desc) {
//            vs.setImageResource(img1);
//            n.setText(name);
//            d.setText(desc);
//        }
    }
}
