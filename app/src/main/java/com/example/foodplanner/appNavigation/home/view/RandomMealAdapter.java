package com.example.foodplanner.appNavigation.home.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
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

public class RandomMealAdapter extends RecyclerView.Adapter<RandomMealAdapter.ViewHolder> {
    private static final String TAG = "RandomMealAdapter";
    private List<Meal> mealList;
    private Context context;
    private OnMealClickListener onMealClickListener;
    private OnMealClicked onMealClicked;

    public RandomMealAdapter(Context context, OnMealClickListener onMealClickListener,OnMealClicked onMealClicked) {
        super();
        this.context = context;
        mealList=new ArrayList<>();
        this.onMealClickListener = onMealClickListener;
        this.onMealClicked = onMealClicked;
    }

    public void setAllMeals(List<Meal> allMeals) {
        this.mealList = allMeals;
    }

    @NonNull
    @Override
    public RandomMealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.meal_card, parent, false);
        Log.i("onCreateViewHolder: ", viewType + "");
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomMealAdapter.ViewHolder holder, int position) {
        Meal meal = mealList.get(holder.getAdapterPosition());
        holder.titleTextView.setText(mealList.get(position).getStrMeal());
        holder.category.setText(mealList.get(position).getStrCategory());
        //holder.bar.setRating((float) mealList.get(position).get);
        Glide.with(context).load(mealList.get(position).strMealThumb)
                .apply(new RequestOptions().override(150, 150))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.productImageView);


        holder.addToFavouriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMealClickListener.addFavor(meal);
                Log.d(TAG, "onClick: " + meal + "position" + position);

            }
        });
        Log.i("onBindViewHolder: ", holder.getAdapterPosition() + "");

        holder.mealCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMealClicked.onMealClicked(mealList.get(position).idMeal);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView productImageView;
        Button addToFavouriteButton;
        RatingBar bar;

        TextView category;
        CardView mealCard;
        public ViewHolder(View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.card_meal_img);
            addToFavouriteButton = itemView.findViewById(R.id.card_add_to_fav);
            titleTextView = itemView.findViewById(R.id.meal_card_title);
            category = itemView.findViewById(R.id.meal_card_category);
            bar = itemView.findViewById(R.id.ratingBar_card);
            mealCard = itemView.findViewById(R.id.mealCard);

        }
    }
}
