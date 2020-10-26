package kr.co.softcampus.tooksampoom.Utils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import kr.co.softcampus.tooksampoom.PushUpFragment;
import kr.co.softcampus.tooksampoom.RunningFragment;
import kr.co.softcampus.tooksampoom.SitUpFragment;

public class MyAdapter extends FragmentStateAdapter {

    public int mCount;

    public MyAdapter(Context context, int count) {
        super((FragmentActivity) context);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if(position==0) return new PushUpFragment();
        else if(position==1) return new SitUpFragment();
        else return new RunningFragment();

    }

    @Override
    public int getItemCount() {
        return mCount;
    }

}
