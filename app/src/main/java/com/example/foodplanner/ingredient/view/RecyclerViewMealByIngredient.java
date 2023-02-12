package com.example.foodplanner.ingredient.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.models.Meal;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewMealByIngredient extends RecyclerView.Adapter<RecyclerViewMealByIngredient.RecyclerViewHolder> {

    private List<Meal> meals;
    private Context context;
    private static ClickListener clickListener;
    private  OnMealClickListener onMealClickListener;

    public RecyclerViewMealByIngredient(Context context, List<Meal> meals, OnMealClickListener onMealClickListener) {
        this.meals = meals;
        this.context = context;
        this.onMealClickListener = onMealClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_meal,
                viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder viewHolder, int i) {

        String strMealThumb = meals.get(i).getStrMealThumb();
        Picasso.get().load(strMealThumb).placeholder(R.drawable.shadow_bottom_to_top).into(viewHolder.mealThumb);

        String strMealName = meals.get(i).getStrCategory();
        viewHolder.mealName.setText(strMealName);
        viewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMealClickListener.addFavor(meals.get(i));
            }
        });
    }


    @Override
    public int getItemCount() {
        return meals.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mealThumb;
        TextView mealName;
        ImageButton add;
        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            mealThumb = itemView.findViewById(R.id.mealThumb);
            mealName = itemView.findViewById(R.id.mealName);
            add = itemView.findViewById(R.id.favBtnn);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewMealByIngredient.clickListener = clickListener;
    }
     public interface ClickListener {
        void onClick(View view, int position);
    }
}
