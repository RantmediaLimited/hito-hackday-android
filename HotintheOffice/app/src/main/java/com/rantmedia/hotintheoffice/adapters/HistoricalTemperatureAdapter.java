package com.rantmedia.hotintheoffice.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rantmedia.hotintheoffice.R;
import com.rantmedia.hotintheoffice.models.TemperatureObject;
import com.rantmedia.hotintheoffice.models.TimeTemperatureObject;
import com.rantmedia.hotintheoffice.utilities.DateHelper;

import net.danlew.android.joda.JodaTimeAndroid;

import java.util.ArrayList;

/**
 * Created by ginobasiletti on 28/07/2017.
 */

public class HistoricalTemperatureAdapter extends RecyclerView.Adapter<HistoricalTemperatureAdapter.TemperatureViewHolder> {
    private Context mContext;
    private Activity mActivity;
    private ArrayList<TimeTemperatureObject> temperatureObjects = new ArrayList<>();


    public HistoricalTemperatureAdapter(Activity activity, Context context, ArrayList<TimeTemperatureObject> temperatureObjects) {
        JodaTimeAndroid.init(context);
        this.mContext = context;
        this.mActivity = activity;
        this.temperatureObjects = temperatureObjects;
    }

    public void swapData(ArrayList<TimeTemperatureObject> temperatureObjects) {
        this.temperatureObjects = temperatureObjects;
        notifyDataSetChanged();
    }


    @Override
    public TemperatureViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.historical_temperature_item_layout, parent, false);
        final TemperatureViewHolder holder = new TemperatureViewHolder(view);


        return holder;
    }


    @Override
    public void onBindViewHolder(final TemperatureViewHolder holder, int position) {
        holder.timeTV.setText(DateHelper.utcToPrettyDate(temperatureObjects.get(holder.getAdapterPosition()).getTime()));
        holder.tempTV.setText(String.valueOf((int) temperatureObjects.get(holder.getAdapterPosition()).getTemperature()) + "°");
    }

    @Override
    public int getItemCount() {
        return temperatureObjects.size();
    }

    class TemperatureViewHolder extends RecyclerView.ViewHolder {
        private final TextView timeTV;
        private final TextView tempTV;

        TemperatureViewHolder(View view) {
            super(view);
            timeTV = (TextView) view.findViewById(R.id.timeTV);
            tempTV = (TextView) view.findViewById(R.id.tempTV);
        }

    }

    //These two methods prevent the recyclerview from repeating items when scrolling quickly
    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

}


