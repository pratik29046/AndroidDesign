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

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Adapters2 extends RecyclerView.Adapter<Adapters2.ViewHolder> {
    private  List<ModelClass4> userList4;
    Context context1;

    public Adapters2(List<ModelClass4> userList4, Context context1){
        this.userList4=userList4;
        this.context1=context1;
    }

    @NonNull
    @Override
    public Adapters2.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main10, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final  ModelClass4 temp1=userList4.get(position);

        int img1=userList4.get(position).getImg();
        holder.setData(img1);
//        holder.n.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(context1,MainActivity7.class);
//                intent.putExtra("vid",temp1.getVid());
////                intent.putExtra("name",temp.getVid());
////                intent.putExtra("img",temp.getVid());
////                intent.putExtra("desc",temp.getVid());
//                context1.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return userList4.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.background);

        }

        public void setData(int img1) {
            img.setImageResource(img1);

        }
    }
}