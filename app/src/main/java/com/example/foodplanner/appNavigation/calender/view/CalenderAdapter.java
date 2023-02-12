package com.example.foodplanner.appNavigation.calender.view;

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
import com.example.foodplanner.MealDetails.view.OnMealClicked;
import com.example.foodplanner.R;
import com.example.foodplanner.models.Meal;

import java.util.ArrayList;
import java.util.List;

public class CalenderAdapter extends RecyclerView.Adapter<CalenderAdapter.ViewHolder>{

    private OnMealClicked onMealClicked;
    private OnCalenderMealClickListener onCalenderMealClickListener;
    Context context;
    List<Meal> scheduledMeals;

    public CalenderAdapter(Context context, OnMealClicked onMealClicked,OnCalenderMealClickListener onCalenderMealClickListener) {
        super();
        this.context = context;
        scheduledMeals=new ArrayList<>();
        this.onMealClicked = onMealClicked;
        this.onCalenderMealClickListener = onCalenderMealClickListener;
    }

    public void setScheduledMealsList(List<Meal> scheduledMeals) {
        this.scheduledMeals = scheduledMeals;
    }

    @NonNull
    @Override
    public CalenderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.calender_item_card, parent, false);
        Log.i("onCreateViewHolder: ", viewType + "");
        return new CalenderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalenderAdapter.ViewHolder holder, int position) {
        Meal meal = scheduledMeals.get(holder.getAdapterPosition());
        holder.titleTextView.setText(scheduledMeals.get(position).getStrMeal());
        holder.category.setText(scheduledMeals.get(position).getStrCategory());
        Glide.with(context).load(scheduledMeals.get(position).strMealThumb)
                .apply(new RequestOptions().override(150, 150))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.productImageView);

        Log.i("onBindViewHolder: ", holder.getAdapterPosition() + "");

        holder.mealCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMealClicked.onMealClicked(scheduledMeals.get(position).idMeal);
            }
        });

        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCalenderMealClickListener.remove(meal);
            }
        });

    }

    @Override
    public int getItemCount() {
        return scheduledMeals.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private ImageView productImageView;
        private TextView category;
        private CardView mealCard;
        private Button removeBtn;
        public ViewHolder(View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.calmealThumb);
            titleTextView = itemView.findViewById(R.id.calName);
            category = itemView.findViewById(R.id.calCategory);
            mealCard = itemView.findViewById(R.id.calenderCard);
            removeBtn = itemView.findViewById(R.id.removeScheduleBtn);
        }
    }
}
