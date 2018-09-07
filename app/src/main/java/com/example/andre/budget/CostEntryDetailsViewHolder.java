package com.example.andre.budget;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

public class CostEntryDetailsViewHolder extends ChildViewHolder {

    private TextView artistName;

    public CostEntryDetailsViewHolder(View itemView) {
        super(itemView);
        artistName = (TextView)itemView.findViewById(R.id.list_item_artist_name);
    }

    public void setArtistName(String name) {
        artistName.setText(name);
    }

}
