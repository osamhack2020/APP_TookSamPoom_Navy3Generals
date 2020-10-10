package kr.co.softcampus.tooksampoom.StartingPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import kr.co.softcampus.tooksampoom.R;

public class Fragment_Fifth extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_fifth, container, false);
 
        return rootView;
    }
 
}