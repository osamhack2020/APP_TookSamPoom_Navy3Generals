package kr.co.softcampus.tooksampoom.StartingPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import kr.co.softcampus.tooksampoom.StartingPage.TspUserData;

public class Fragment_Second extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_second, container, false); 
            
        ImageButton MaleBtn = (ImageButton)findViewById(R.id.MaleBtn);
        ImageButton FemaleBtn = (ImageButton)findViewById(R.id.FemaleBtn);
        

        MaleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TspUserData.Gender = MaleBtn.getText().toString();
                mPager.setCurrentItem(mPager.getCurrentItem()+1);
            }
        });
        FemaleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TspUserData.Gender = FemaleBtn.getText().toString();
                mPager.setCurrentItem(mPager.getCurrentItem()+1);
            }
        });
        return rootView;
        
    }
}