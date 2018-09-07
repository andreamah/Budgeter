package com.example.andre.budget;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

//genre
public class CostEntryViewHolder extends GroupViewHolder {
    private TextView genreName;

    public CostEntryViewHolder(View itemView) {
        super(itemView);
        genreName = (TextView)itemView.findViewById(R.id.list_item_genre_name);
    }

    public void setGenreName(String name) {
        genreName.setText(name);
    }
}
