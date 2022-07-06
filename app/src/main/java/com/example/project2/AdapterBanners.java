package com.example.project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.POJO.Banner;
import com.example.project2.POJO.Episode;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterBanners extends RecyclerView.Adapter<AdapterBanners.View_Holder> {

    private List<Banner> bannerList;
    Context context1;


    public AdapterBanners(List<Banner> bannerList,Context context1){
        this.bannerList=bannerList;
        this.context1=context1;
    }

    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.banners, parent, false);
        return new View_Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
        Picasso.get().load("https://katto.in"+bannerList.get(position).poster).into( holder.img1);

    }

    @Override
    public int getItemCount() {
        return bannerList.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder {
        ImageView img1;
        public View_Holder(@NonNull View itemView) {
            super(itemView);

            img1=itemView.findViewById(R.id.img1);
        }
    }
}
