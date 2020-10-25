package com.example.android3_lesosn3.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.android3_lesosn3.R;
import com.example.android3_lesosn3.data.adapters.FragmentAdapter;
import com.example.android3_lesosn3.ui.collections.CollectionsFragment;
import com.example.android3_lesosn3.ui.favorite.FavoriteFragment;
import com.example.android3_lesosn3.ui.form.FormActivity;
import com.example.android3_lesosn3.ui.list.ListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();




        fragmentList = new ArrayList<>();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        viewPager = findViewById(R.id.view_pager);
        fillFragment();

        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragmentList));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_list){
                    viewPager.setCurrentItem(0);
                } else if (item.getItemId() == R.id.menu_favorite){
                    viewPager.setCurrentItem(1);
                } else if (item.getItemId() == R.id.menu_collections){
                    viewPager.setCurrentItem(2);
                }
                return true;
            }
        });


    }




    public void fillFragment(){
        fragmentList.add(new ListFragment());
        fragmentList.add(new FavoriteFragment());
        fragmentList.add(new CollectionsFragment());
    }
}