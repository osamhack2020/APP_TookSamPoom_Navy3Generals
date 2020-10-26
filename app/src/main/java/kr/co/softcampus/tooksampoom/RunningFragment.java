package kr.co.softcampus.tooksampoom;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RunningFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_running, container, false);

        return rootView;
    }
    public void onClickRun(View view) {
        Intent runningIntent = new Intent(getActivity(), RunningActivity.class);
        startActivity(runningIntent);
    }
}