package com.example.foodplanner.MealDetails.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder>{
    private final Context context;
    private String[] values;
    private static final String TAG ="RecyclerView";

    public StepsAdapter(Context context,String[] data){

        this.context = context;
        values= data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.step_card,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          holder.stepTxt.setText(values[position]);
    }

    @Override
    public int getItemCount() {
        return values.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView stepTxt;
        public View view;

        public ViewHolder(View v){
            super(v);
            view = v;
            stepTxt = v.findViewById(R.id.stepTxt);
        }
    }

}
