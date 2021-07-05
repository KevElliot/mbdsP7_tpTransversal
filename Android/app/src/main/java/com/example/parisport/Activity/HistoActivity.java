package com.example.parisport.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.parisport.Adapter.ViewPagerAdapter;
import com.example.parisport.Fragments.HistoAllFragment;
import com.example.parisport.Fragments.HistoInProgressFragment;
import com.example.parisport.Fragments.HistoWinFragment;
import com.example.parisport.R;
import com.google.android.material.tabs.TabLayout;

public class HistoActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        viewPagerAdapter.addFragment(new HistoAllFragment(), "Tous");
        viewPagerAdapter.addFragment(new HistoInProgressFragment(), "Encours");
        viewPagerAdapter.addFragment(new HistoWinFragment(), "Gagnes");
        viewPager.setAdapter(viewPagerAdapter);

    }
}