package hu.ait.demos.placestovisit.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import hu.ait.demos.placestovisit.ApplicationComponent;
import hu.ait.demos.placestovisit.MainActivity;
import hu.ait.demos.placestovisit.PlacesApplication;
import hu.ait.demos.placestovisit.R;
import hu.ait.demos.placestovisit.data.Place;

/**
 * Created by Peter on 2015.07.01..
 */
@Singleton
public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView ivIcon;
        public TextView tvPlace;
        public TextView tvDate;
        public Button btnDelete;
        public Button btnEdit;

        public ViewHolder(View itemView) {
            super(itemView);
            ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);
            tvPlace = (TextView) itemView.findViewById(R.id.tvPlace);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);
        }
    }

    private List<Place> placesList;
    private Context context;
    private int lastPosition = -1;

    public PlacesAdapter(Context context, List<Place> placesList) {
        this.placesList = placesList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_place, viewGroup, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        viewHolder.tvPlace.setText(placesList.get(position).getPlaceName());
        viewHolder.tvDate.setText(placesList.get(position).getPickUpDate().toString());
        viewHolder.ivIcon.setImageResource(
                placesList.get(position).getPlaceType().getIconId());

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removePlace(position);
            }
        });
        viewHolder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).showEditPlaceActivity(placesList.get(position), position);
            }
        });

        setAnimation(viewHolder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }

    public void addPlace(Place place) {
        place.save();
        placesList.add(place);
        notifyDataSetChanged();
    }

    public void updatePlace(int index, Place place) {
        placesList.set(index, place);
        place.save();
        notifyItemChanged(index);
    }

    public void removePlace(int index) {
        // remove it from the DB
        placesList.get(index).delete();
        // remove it from the list
        placesList.remove(index);
        notifyDataSetChanged();
    }

    public void swapPlaces(int oldPosition, int newPosition) {
        if (oldPosition < newPosition) {
            for (int i = oldPosition; i < newPosition; i++) {
                Collections.swap(placesList, i, i + 1);
            }
        } else {
            for (int i = oldPosition; i > newPosition; i--) {
                Collections.swap(placesList, i, i - 1);
            }
        }
        notifyItemMoved(oldPosition, newPosition);
    }

    public Place getPlace(int i) {
        return placesList.get(i);
    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

}
