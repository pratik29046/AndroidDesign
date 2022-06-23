package com.example.project2;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.POJO.Category;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    List<Category> mainPOJOSlist;
    public Adapter(Context context, List<Category> mainPOJOSlist){
        this.context = context;
        this.mainPOJOSlist = mainPOJOSlist;

    }

    @NonNull
    @NotNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main3, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull final Adapter.ViewHolder h, int position) {

        h.rowtitle.setText(mainPOJOSlist.get(position).name);
        categeriouschlidAdapter chailedAdapter = new categeriouschlidAdapter(context,mainPOJOSlist.get(position).content);
        h.recyclerView.setLayoutManager(new LinearLayoutManager(context,RecyclerView.HORIZONTAL,false));
       h.recyclerView.setAdapter(chailedAdapter);

//        final Category temp=mainPOJOSlist.get(position);
//        Log.d("TAG", "onBindViewHolder: "+mainPOJOSlist.get(position).name+"  : "+mainPOJOSlist.get(position).content.toString());
    }

    @Override
    public int getItemCount() {
        return mainPOJOSlist.size();
    }


    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView rowtitle;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowtitle=itemView.findViewById(R.id.names);
            recyclerView=itemView.findViewById(R.id.review2);
        }
    }
}
