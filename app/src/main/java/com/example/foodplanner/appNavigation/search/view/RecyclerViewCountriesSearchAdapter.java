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
import com.example.foodplanner.models.Country;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewCountriesSearchAdapter extends RecyclerView.Adapter<RecyclerViewCountriesSearchAdapter.RecyclerViewHolder> {

    private List<Country> countries;
    private Context context;
    private static ClickListener clickListener;

    public RecyclerViewCountriesSearchAdapter(List<Country> countries, Context context) {
        this.countries = countries;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewCountriesSearchAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recycler_country,viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewCountriesSearchAdapter.RecyclerViewHolder viewHolder, int i) {

        Picasso.get()
                .load(String.format(countries.get(i).getStrArea()))
                .placeholder(R.drawable.ic_circle)
                .error(R.drawable.ic_launcher_foreground)
                .into(viewHolder.countryThumb);

        String strIngredientName = countries.get(i).getStrArea();
        viewHolder.countryName.setText(strIngredientName);
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView countryThumb;
        TextView countryName;
        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            countryThumb = itemView.findViewById(R.id.countryThumb);
            countryName = itemView.findViewById(R.id.countryName);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        RecyclerViewCountriesSearchAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }
}
