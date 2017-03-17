package com.pragma.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pragma.presentation.R;
import com.pragma.presentation.model.RestaurantModel;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by anbu.ezhilan on 17/03/2017.
 */

public class RestaurantsAdapter extends
        RecyclerView.Adapter<RestaurantsAdapter.RestaurantsViewHolder> {



    public interface OnItemClickListener {
        void onRestaurantItemClicked(RestaurantModel restaurantModel);
    }

    private List<RestaurantModel> restaurantsCollection;
    private final LayoutInflater layoutInflater;

    private OnItemClickListener onItemClickListener;

    @Inject
    RestaurantsAdapter(Context context) {
        this.layoutInflater = (LayoutInflater) context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        this.restaurantsCollection = Collections.emptyList();
    }


    @Override
    public int getItemCount() {
        return (this.restaurantsCollection != null ? this.restaurantsCollection.size() : 0);
    }

    @Override
    public RestaurantsAdapter.RestaurantsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.row_restaurants, parent, false);
        return new RestaurantsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RestaurantsAdapter.RestaurantsViewHolder holder, int position) {

    }

    static class RestaurantsViewHolder extends RecyclerView.ViewHolder {

        RestaurantsViewHolder(View itemView) {
            super(itemView);
        }
    }

}
