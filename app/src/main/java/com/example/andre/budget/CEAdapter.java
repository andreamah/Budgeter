package com.example.andre.budget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class CEAdapter extends ExpandableRecyclerViewAdapter<CostEntryViewHolder,CostEntryDetailsViewHolder>{

    public CEAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public CostEntryDetailsViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_details,parent,false);
        return new CostEntryDetailsViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(CostEntryDetailsViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        CostEntry costEntry = (CostEntry) group.getItems().get(childIndex);
        holder.setArtistName(costEntry.getName());
    }

    @Override
    public void onBindGroupViewHolder(CostEntryViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGenreName(group.getTitle());
    }

    @Override
    public CostEntryViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_header,parent,false);
        return new CostEntryViewHolder(view);
    }

}
