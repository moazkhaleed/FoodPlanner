package com.example.foodplanner.appNavigation.search.view;

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
import com.example.foodplanner.MealDetails.view.OnMealClicked;
import com.example.foodplanner.R;
import com.example.foodplanner.appNavigation.home.view.OnMealClickListener;
import com.example.foodplanner.appNavigation.home.view.TrendingAdapter;
import com.example.foodplanner.models.Meal;

import java.util.ArrayList;
import java.util.List;


public class SearchByNameAdapter  extends RecyclerView.Adapter<SearchByNameAdapter.ViewHolder>{
    private static final String TAG = "SearchByName";
    private List<Meal> mealList;
    private Context context;
    private OnMealClickListener onMealClickListener;
    private OnMealClicked onMealClicked;

    public SearchByNameAdapter(Context context, OnMealClicked onMealClicked) {
        super();
        this.context = context;
        mealList=new ArrayList<>();
        this.onMealClicked = onMealClicked;

    }

    public void setAllMeals(List<Meal> allMeals) {
        this.mealList = allMeals;
    }

    @NonNull
    @Override
    public SearchByNameAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.search_by_name_item_card, parent, false);
        Log.i("onCreateViewHolder: ", viewType + "");
        return new SearchByNameAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchByNameAdapter.ViewHolder holder, int position) {
        Meal meal = mealList.get(holder.getAdapterPosition());
        holder.titleTextView.setText(mealList.get(position).getStrMeal());
        holder.category.setText(mealList.get(position).getStrCategory());
        Glide.with(context).load(mealList.get(position).strMealThumb)
                .apply(new RequestOptions().override(150, 150))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.productImageView);

        holder.mealCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMealClicked.onMealClicked(meal.idMeal);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private ImageView productImageView;
        private TextView category;
        private CardView mealCard;
        public ViewHolder(View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.favmealThumb);
            titleTextView = itemView.findViewById(R.id.FavName);
            category = itemView.findViewById(R.id.favCategory);
            mealCard = itemView.findViewById(R.id.favCard);
        }
    }

}
