package com.tripbuddy;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tripbuddy.models.Trip;

import org.parceler.Parcels;

import java.util.List;

public class TripsAdapter extends RecyclerView.Adapter<TripsAdapter.ViewHolder> {
    public static final String TAG = "TripsAdapter";
    Context context;
    List<Trip> trips;

    public TripsAdapter(Context c, List<Trip> t) {
        this.context = c;
        this.trips = t;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_trip, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TripsAdapter.ViewHolder holder, int position) {
        Trip trip = trips.get(position);
        holder.bind(trip);
    }

    @Override
    public int getItemCount() {
        return this.trips.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;
        TextView tvDestination;
        TextView tvDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDestination = itemView.findViewById(R.id.tvDestination);
            tvDate = itemView.findViewById(R.id.tvDate);

            itemView.setOnClickListener(this);
        }

        public void bind(Trip trip) {
            tvTitle.setText(trip.getTitle());
            tvDestination.setText(trip.getDestination());
            // TODO: update Trip.java to return date as string
            tvDate.setText("June 1 - June 8");
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Log.i(TAG, "item clicked at position " + Integer.toString(position));
            if (position != RecyclerView.NO_POSITION) {
                Trip selected = trips.get(position);
                Intent i = new Intent(context, TripDetailActivity.class);
                i.putExtra(Trip.class.getSimpleName(), Parcels.wrap(selected));
                context.startActivity(i);
            }
        }
    }

}
