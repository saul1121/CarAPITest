package com.app.carapitest.adapters;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.app.carapitest.R;
import com.app.carapitest.activities.DetailActivity;
import com.app.carapitest.models.CarItem;


public class CarAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private List<CarItem> mItems;
    private List<CarItem> itemsAll;

    public CarAdapter(Context context, List<CarItem> items) {
        mContext = context;
        mItems = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.row_linear, viewGroup, false);
        return new CarViewHolder(mContext, v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        ((CarViewHolder) viewHolder).invalidate(mItems.get(i), i);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void addAll(List<CarItem> items) {
        itemsAll = items;
        addmore();
    }

    public List<CarItem> getItems() {
        return mItems;
    }

    public void addmore() {
        if(itemsAll.size() > 40) {
            for (int i = 0; i < 20; i++) {
                mItems.add(itemsAll.remove(i));
            }
        }
    }


    private class CarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title;
        CarItem car;

        public CarViewHolder(Context c, View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putString("name", car.getMakeNiceName());
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtras(bundle);
            mContext.startActivity(intent);
        }

        public void invalidate(CarItem car, int i) {
            title.setText(car.getMakeNiceName() + " - " + car.getModelNiceName() + "\n" + car.getYear());
            this.car = car;
        }
    }
}
