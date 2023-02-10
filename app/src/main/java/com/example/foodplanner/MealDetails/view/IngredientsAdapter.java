package com.example.foodplanner.MealDetails.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder>{
    private final Context context;
    private List<String> values;
    private static final String TAG ="RecyclerView";

    public IngredientsAdapter(Context context, List<String> values){
        this.context = context;
        this.values = values;
    }

    @NonNull
    @Override
    public IngredientsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.ingredients_card,parent,false);
        IngredientsAdapter.ViewHolder vh = new IngredientsAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.ViewHolder holder, int position) {
        holder.ingredirnt.setText(values.get(position));
    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView ingredirnt;
        public View view;

        public ViewHolder(View v){
            super(v);
            view = v;
            ingredirnt = v.findViewById(R.id.ingredientTxt);
        }
    }
}
