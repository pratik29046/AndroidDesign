package com.example.project2;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<ModelClass> userList;
    private  Recycler recycler;

    public Adapter(List<ModelClass> userList,Recycler recycler){
        this.userList=userList;
        this.recycler=recycler;
    }



    @NonNull
    @NotNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main3, parent, false);


        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapter.ViewHolder h, int position) {
        int img =userList.get(position).getImg();

        h.setData(img);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView i1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            i1 =itemView.findViewById(R.id.img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recycler.onItemClick(getAdapterPosition());
                }
            });

        }

        public void setData(int img) {
            i1.setImageResource(img);


        }


    }
}
