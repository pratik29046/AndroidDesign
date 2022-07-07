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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdaptersSearchNames extends RecyclerView.Adapter<AdaptersSearchNames.ViewHolder> implements Filterable {
    private List<Content> contents;
    Context context1;

    List<Content> mStringFilterList;
    ValueFilter valueFilter;

    public AdaptersSearchNames(List<Content> contents, Context context1){
        this.contents=contents;
        this.context1=context1;
        mStringFilterList=contents;
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

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }


    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<Content> filterList=new ArrayList<>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if (mStringFilterList.get(i).name.toUpperCase(Locale.ROOT).contains(constraint.toString().toUpperCase(Locale.ROOT))) {
                        filterList.add(mStringFilterList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            contents = (List<Content>) results.values;
            notifyDataSetChanged();
        }

    }



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
