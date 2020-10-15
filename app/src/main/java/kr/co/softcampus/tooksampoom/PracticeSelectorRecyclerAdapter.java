package kr.co.softcampus.tooksampoom;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.List;

public class PracticeSelectorRecyclerAdapter extends RecyclerView.Adapter<PracticeSelectorRecyclerAdapter.ViewHolder> {
    private List<String> mData;
    private LayoutInflater mInflater;
    private ViewPager2 viewPager2;


    public PracticeSelectorRecyclerAdapter(Context context, List<String> data, ViewPager2 viewPager2) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.viewPager2 = viewPager2;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_practice_selector, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String animal = mData.get(position);
        holder.myTextView.setText(animal);
        holder.itemView.setBackgroundColor(Color.BLUE);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.practice_selector_textview);

        }
    }
}
