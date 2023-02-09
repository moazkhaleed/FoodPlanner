package com.example.foodplanner.appNavigation.favourites.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.MealDeails.view.OnMealClicked;
import com.example.foodplanner.R;
import com.example.foodplanner.models.Meal;

import java.util.ArrayList;
import java.util.List;

public class FavMealAdapter extends RecyclerView.Adapter<FavMealAdapter.ViewHolder> {
    onFavMealClickListener onFavMealClickListener;
    private OnMealClicked onMealClicked;
     Context context;
     List<Meal> favMealList;

    public FavMealAdapter(Context context, onFavMealClickListener onFavMealClickListener, OnMealClicked onMealClicked) {
        super();
        this.context = context;
        favMealList=new ArrayList<>();
        this.onFavMealClickListener = onFavMealClickListener;
        this.onMealClicked = onMealClicked;
    }

    public void setFavMealsList(List<Meal> favMeals) {
        this.favMealList = favMeals;
    }


    @NonNull
    @Override
    public FavMealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fav_item_recycler_meal, parent, false);
        Log.i("onCreateViewHolder: ", viewType + "");
        return new FavMealAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavMealAdapter.ViewHolder holder, int position) {
        Meal meal = favMealList.get(holder.getAdapterPosition());
        holder.titleTextView.setText(favMealList.get(position).getStrMeal());
        holder.category.setText(favMealList.get(position).getStrCategory());
        Glide.with(context).load(favMealList.get(position).strMealThumb)
                .apply(new RequestOptions().override(150, 150))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.productImageView);


        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFavMealClickListener.remove(meal);
            }
        });
        Log.i("onBindViewHolder: ", holder.getAdapterPosition() + "");

        holder.mealCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMealClicked.onMealClicked(favMealList.get(position).idMeal);
            }
        });

    }

    @Override
    public int getItemCount() {
        return favMealList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView productImageView;
        Button removeButton;
        TextView category;
        CardView mealCard;
        public ViewHolder(View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.favmealThumb);
            removeButton = itemView.findViewById(R.id.removeBtn);
            titleTextView = itemView.findViewById(R.id.FavName);
            category = itemView.findViewById(R.id.favCategory);
            mealCard = itemView.findViewById(R.id.favCard);


        }
    }

}
