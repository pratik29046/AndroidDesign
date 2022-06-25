package com.example.project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.POJO.Category;
import com.example.project2.POJO.WelcomeBanner;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterWelcome extends RecyclerView.Adapter<AdapterWelcome.ViewHolder> {

    Context context;
    List<WelcomeBanner> welcomeBanners;
    public AdapterWelcome(Context context, List<WelcomeBanner> welcomeBanners){
        this.context = context;
        this.welcomeBanners = welcomeBanners;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.welcomepage, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.text.setText(welcomeBanners.get(position).description);
        Picasso.get().load("https://katto.in"+welcomeBanners.get(position).poster).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return welcomeBanners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         ImageView img;
         TextView text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img=itemView.findViewById(R.id.img1);
            text=itemView.findViewById(R.id.text3);
        }
    }
}
