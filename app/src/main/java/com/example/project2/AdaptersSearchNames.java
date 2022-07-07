package com.example.project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.POJO.Content;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptersSearchNames extends RecyclerView.Adapter<AdaptersSearchNames.ViewHolder> {
    private List<Content> contents;
    Context context1;

    public AdaptersSearchNames(List<Content> contents, Context context1){
        this.contents=contents;
        this.context1=context1;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchbar_items, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load("https://katto.in"+contents.get(position).poster).into( holder.poster);
        holder.name.setText(contents.get(position).name);
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

//    @Override
//    public Filter getFilter() {
//        return exampleFilter;
//    }

//    private Filter exampleFilter = new Filter() {
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            if (constraint == null || constraint.length() == 0) {
//                contents.addAll(exampleListFull);
//            } else {
//                String filterPattern = constraint.toString().toLowerCase().trim();
//                for (Content item : exampleListFull) {
//                    if ( item.name.toLowerCase().contains(filterPattern)) {
//                        contents.add(item);
//                    }
//                }
//            }
//            FilterResults results = new FilterResults();
//            results.values = contents;
//            return results;
//        }
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            contents.clear();
//            contents.addAll((List) results.values);
//            notifyDataSetChanged();
//        }
//    };



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView poster;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            poster=itemView.findViewById(R.id.img1);
            name=itemView.findViewById(R.id.text1);
        }
    }
}
