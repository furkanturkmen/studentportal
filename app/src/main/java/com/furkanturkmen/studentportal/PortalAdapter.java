package com.furkanturkmen.studentportal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

public class PortalAdapter extends RecyclerView.Adapter<PortalAdapter.ViewHolder> {
    private List<Portal> mPortals;
    final private PortalClickListener mPortalClickListener;

    public interface PortalClickListener{
        void PortalOnClick (int i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            mPortalClickListener.PortalOnClick(clickedPosition);
        }
    }

    public PortalAdapter(List<Portal> Portals, PortalClickListener mPortalClickListener) {
        this.mPortals = Portals;
        this.mPortalClickListener = mPortalClickListener;
    }

    @Override
    public PortalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(android.R.layout.simple_list_item_1, null);

        // Return a new holder instance

        PortalAdapter.ViewHolder viewHolder = new PortalAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PortalAdapter.ViewHolder holder, int position) {

        Portal portal = mPortals.get(position);

        holder.textView.setText(portal.getName() + "\n" + portal.getUrl());
    }

    @Override
    public int getItemCount() {
        return mPortals.size();
    }
}