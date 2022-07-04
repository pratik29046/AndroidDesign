package com.example.project2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2.POJO.MembershipPlan;
import com.example.project2.POJO.Upcoming;
import com.google.android.exoplayer2.C;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterPlans extends RecyclerView.Adapter<AdapterPlans.View_Holder> {

    Context context;
    List<MembershipPlan> membershipPlans;

    public AdapterPlans(Context context,List<MembershipPlan> membershipPlans){
        this.context = context;
        this.membershipPlans =membershipPlans;
    }



    @NonNull
    @Override
    public View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.plans_subscription, parent, false);
        return new View_Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull View_Holder holder, int position) {
        holder.name.setText(membershipPlans.get(position).name);
//        holder.price.setText(membershipPlans.get(position).price);
//        holder.validity.setText(membershipPlans.get(position).validity);
        holder.price.setText(String.format("%s %s", context.getResources().getString(R.string.rupee), membershipPlans.get(position).price));
        holder.validity.setText(String.format("Valid for %d days Plan", membershipPlans.get(position).validity));
        holder.buy.setText(String.format("Continue with %s %s", context.getResources().getString(R.string.rupee), membershipPlans.get(position).price));
    }

    @Override
    public int getItemCount() {
        return membershipPlans.size();
    }

    public class View_Holder extends RecyclerView.ViewHolder {
        TextView name,price,validity,buy;
        public View_Holder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.month);
            price=itemView.findViewById(R.id.price);
            validity=itemView.findViewById(R.id.numberOfDays);
            buy=itemView.findViewById(R.id.buy);
        }
    }
}
