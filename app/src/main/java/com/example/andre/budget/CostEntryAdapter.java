package com.example.andre.budget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;



public class CostEntryAdapter extends RecyclerView.Adapter<CostEntryAdapter.CostEntryViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<CostEntry> productList;
    int mExpandedPosition = -1;
    //getting the context and product list with constructor
    public CostEntryAdapter(Context mCtx, List<CostEntry> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public CostEntryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_costentry, null);
        return new CostEntryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CostEntryViewHolder holder, int position) {
        //getting the product of the specified position
        CostEntry product = productList.get(position);

        //binding the data with the viewholder views
        holder.textName.setText(product.getName());
        holder.textDesc.setText(product.getDescription());
        holder.textDate.setText(String.valueOf(product.getDate()));
        holder.textCost.setText(String.valueOf("$"+String.format("%.2f", product.getCost())));

//        final boolean isExpanded = position==mExpandedPosition;
//        holder.cardDetails.setVisibility(isExpanded?View.VISIBLE:View.GONE);
//        holder.itemView.setActivated(isExpanded);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                mExpandedPosition = isExpanded ? -1:position;
//                TransitionManager.beginDelayedTransition(recyclerView);
//                notifyDataSetChanged();
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return productList.size();
    }


    class CostEntryViewHolder extends RecyclerView.ViewHolder {

        TextView textName, textDesc, textCost, textDate;


        public CostEntryViewHolder(View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textName);
            textCost = itemView.findViewById(R.id.textCost);
            textDate = itemView.findViewById(R.id.textDate);
            textDesc = itemView.findViewById(R.id.textDesc);
        }
    }
}