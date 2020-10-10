package kr.co.softcampus.tooksampoom.StartingPage;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

public class StartViewPager extends FragmentActivity {

    public static ViewPager2 mPager;
    public static FragmentStateAdapter pagerAdapter;
    public static int num_page = 5;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
 
        //ViewPager2
        mPager =(ViewPager)findViewById(R.id.pager);
        //Adapter
        pagerAdapter = new FragmentAdapter(this, num_page);
        mPager.setAdapter(pagerAdapter);
        //ViewPager Setting
        mPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mPager.setCurrentItem(50);
        mPager.setOffscreenPageLimit(3);

        layoutInflater inflater = getLayoutInflater();
        View nameview = inflater.inflate(R.layout.fragment_first, null);
        View genderview = inflater.inflate(R.layout.fragment_second, null);
        View heightview = inflater.inflate(R.layout.fragment_third, null);

        viewList.add(nameview);
        viewList.add(genderview);
        viewList.add(heightview);
 
        class cumtumAdapter extends PagerAdapter{
            @Override
            public int getCount() {
                return 0;
            }

        }
 
 
        final float pageMargin= getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        final float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);
 
        mPager.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float myOffset = position * -(2 * pageOffset + pageMargin);
                if (mPager.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                    if (ViewCompat.getLayoutDirection(mPager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                        page.setTranslationX(-myOffset);
                    } else {
                        page.setTranslationX(myOffset);
                    }
                } else {
                    page.setTranslationY(myOffset);
                }
            }
        });
 
    }
 
}
