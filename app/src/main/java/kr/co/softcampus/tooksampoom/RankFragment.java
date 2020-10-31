package kr.co.softcampus.tooksampoom;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class RankFragment extends Fragment {

    View view;
    ListView listView;
    //서버에서 데이터 받아오기
    String[] data1={
        "김기태", "김민철", "유재석", "홍길동", "이상화", "이천수", "안정환", "정민석", "태연","아이린"
    };
    String[] data2={
        "5.6", "5.3", "5.2", "5.1", "4.7", "4.4", "4.3", "3.2","3.1","2.6"
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_rank,container, false);
        listView = view.findViewById(R.id.list1);
        ArrayList<HashMap<String, Object>> data_list = new ArrayList<HashMap<String,Object>>();

        for(int i=0; i<data1.length; i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", (i+1)+"위 "+ data1[i]);
            map.put("score", "평점: "+data2[i]);
            data_list.add(map);
        }

        int[] ids = {R.id.textView10, R.id.textView11};

        String[] keys = {"name", "score"};

        SimpleAdapter adapter = new SimpleAdapter(getActivity(), data_list, R.layout.listview_item, keys, ids);
        listView.setAdapter(adapter);

        return view;
    }
}