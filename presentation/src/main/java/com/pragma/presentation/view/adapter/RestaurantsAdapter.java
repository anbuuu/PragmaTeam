package com.pragma.presentation.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pragma.presentation.R;
import com.pragma.presentation.model.RestaurantModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

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
        final RestaurantModel restaurantModel = this.restaurantsCollection.get(position);
        holder.textViewTitle.setText(restaurantModel.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(RestaurantsAdapter.this.onItemClickListener != null) {
                    RestaurantsAdapter.this.onItemClickListener.onRestaurantItemClicked(restaurantModel);
                }
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public void setRestaurantsCollection(Collection<RestaurantModel> restaurantsCollection) {
        this.validateRestaurantsCollection(restaurantsCollection);
        this.restaurantsCollection = (List<RestaurantModel>) restaurantsCollection;
        this.notifyDataSetChanged();
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    private void validateRestaurantsCollection(Collection<RestaurantModel> restaurantModelCollection) {
        if ( restaurantModelCollection == null ) {
            throw new IllegalArgumentException("The list cannot be null");
        }
    }
    static class RestaurantsViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.rowTitle)
        TextView textViewTitle;
        RestaurantsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
