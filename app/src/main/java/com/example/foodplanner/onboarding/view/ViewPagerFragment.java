package com.example.foodplanner.onboarding.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.onboarding.view.screens.FirstScreenFragment;
import com.example.foodplanner.onboarding.view.screens.SecondScreenFragment;
import com.example.foodplanner.onboarding.view.screens.ThirdScreenFragment;

import java.util.ArrayList;
import java.util.List;


public class ViewPagerFragment extends Fragment {

    private List<Fragment> fragments;
    private ViewPagerAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager2 viewPager = view.findViewById(R.id.view_pager_id);
        fragments=new ArrayList<>();
        fragments.add(new FirstScreenFragment());
        fragments.add(new SecondScreenFragment());
        fragments.add(new ThirdScreenFragment());

        adapter =  new ViewPagerAdapter(requireActivity().getSupportFragmentManager(), getLifecycle(), fragments);
        viewPager.setAdapter(adapter);

    }
}