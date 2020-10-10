package kr.co.softcampus.tooksampoom.StartingPage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import kr.co.softcampus.tooksampoom.MainActivity;

public class FragmentAdapter extends FragmentStateAdapter {
 
    public int mCount;
 
    public FragmentAdapter(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }
 
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);
 
        if(index==0) return new FragFirst();
        else return new MainActivity();
 
    }
 
    @Override
    public int getItemCount() {
        return 100;
    }
 
    public int getRealPosition(int position) { return position % mCount; }
 
}