package kr.co.softcampus.tooksampoom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class PracticeSelectorActivity extends AppCompatActivity {

    ViewPager2 viewPager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_selector);
        viewPager2 = findViewById(R.id.pager);

        List<String> list = new ArrayList<>();
        list.add("팔 굽혀펴기");
        list.add("윗몸 일으키기");
        list.add("3KM 달리기");

        viewPager2.setAdapter(new PracticeSelectorRecyclerAdapter(this, list, viewPager2));


        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
        });

    }
}