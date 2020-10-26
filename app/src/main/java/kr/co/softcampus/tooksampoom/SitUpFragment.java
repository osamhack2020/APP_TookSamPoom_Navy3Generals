package kr.co.softcampus.tooksampoom;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SitUpFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_sit_up, container, false);

        return rootView;
    }
    public void onClickSU(View view) {
        Intent siIntent = new Intent(getActivity(), SitUpMeasureActivity.class);
        startActivity(siIntent);
    }
}