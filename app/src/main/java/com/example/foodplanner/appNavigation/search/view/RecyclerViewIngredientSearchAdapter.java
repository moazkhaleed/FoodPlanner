package com.example.foodplanner.appNavigation.search.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodplanner.R;
import com.example.foodplanner.models.Ingredient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewIngredientSearchAdapter extends RecyclerView.Adapter<RecyclerViewIngredientSearchAdapter.RecyclerViewHolder> {

    private List<Ingredient> ingredients;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewIngredientSearchAdapter(List<Ingredient> ingredients, Context context) {
        this.ingredients = ingredients;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewIngredientSearchAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_ingredient,viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewIngredientSearchAdapter.RecyclerViewHolder viewHolder, int i) {

        Picasso.get().load(String.format("https://www.themealdb.com/images/ingredients/%s-Small.png",ingredients.get(i).getStrIngredient())).placeholder(R.drawable.ic_circle).error(R.drawable.ic_launcher_foreground).into(viewHolder.ingredientThumb);

        String strIngredientName = ingredients.get(i).getStrIngredient();
        viewHolder.ingredientName.setText(strIngredientName);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ingredientThumb;
        TextView ingredientName;
        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            ingredientThumb = itemView.findViewById(R.id.ingredientThumb);
            ingredientName = itemView.findViewById(R.id.ingredientName);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewIngredientSearchAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }
}
