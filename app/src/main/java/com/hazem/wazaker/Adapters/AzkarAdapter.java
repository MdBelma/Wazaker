package com.hazem.wazaker.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.hazem.wazaker.Activites.AzkarListActivity;
import com.hazem.wazaker.Models.Rec_azkar_items;
import com.hazem.wazkar.R;
import com.hazem.wazaker.listeners.ItemListner;

import java.util.ArrayList;
import java.util.List;

public class AzkarAdapter extends RecyclerView.Adapter<AzkarAdapter.ViewHolder> {



    private ItemListner listner;
    private List<Rec_azkar_items> items;


    public AzkarAdapter(List<Rec_azkar_items> items, Context context) {
        this.items = items;
    }




    public void setListner(ItemListner listner) {
        this.listner = listner;
    }

    @Override
    public AzkarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.simple_cell, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        Rec_azkar_items p = items.get(position);
        holder.title.setText(p.getName());
       
    }

    @Override
    public int getItemCount() {

        return items.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            itemView.setOnClickListener((e) -> {
                listner.onItemCLicked(getAdapterPosition());
            });
        }
    }

}

