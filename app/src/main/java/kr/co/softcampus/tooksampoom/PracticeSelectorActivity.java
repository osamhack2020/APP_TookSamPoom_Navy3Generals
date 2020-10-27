package kr.co.softcampus.tooksampoom;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import kr.co.softcampus.tooksampoom.Utils.MyAdapter;
import kr.co.softcampus.tooksampoom.Utils.PracticeSelectorRecycleAdapter;


public class PracticeSelectorActivity extends AppCompatActivity {

    ProgressBar progressBar;
    ViewPager2 viewPager2;
    FragmentStateAdapter pagerAdapter;
    ImageView push_up_icon;
    ImageView sit_up_icon;
    ImageView running_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_selector);
        viewPager2 = findViewById(R.id.pager);
        progressBar = findViewById(R.id.progressBar);
        push_up_icon = findViewById(R.id.push_up_icon);
        sit_up_icon = findViewById(R.id.sit_up_icon);
        running_icon = findViewById(R.id.running_icon);

        pagerAdapter = new MyAdapter(this, 3);
        viewPager2.setAdapter(pagerAdapter);
        viewPager2.setCurrentItem(0);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(position==0){
                    progressBar.setProgress(0);

                }
                if(position==1){
                    progressBar.setProgress(50);

                }
                if(position==2){
                    progressBar.setProgress(100);

                }
            }
        });

    }

    public void onClickSU(View view) {
        Intent siIntent = new Intent(this, SitUpMeasureActivity.class);
        startActivity(siIntent);
    }
    public void onClickRun(View view) {
        Intent runningIntent = new Intent(this, RunningActivity.class);
        startActivity(runningIntent);
    }

    public void onClickPU(View view) {
        Intent puIntent = new Intent(this, PushUpMeasureActivity.class);
        startActivity(puIntent);
    }
}