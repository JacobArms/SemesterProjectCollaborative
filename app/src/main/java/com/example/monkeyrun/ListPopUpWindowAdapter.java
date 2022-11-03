package com.example.monkeyrun;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ListPopUpWindowAdapter extends BaseAdapter {
    private Activity OpeningScreenActivity;
    private List<String> mDataSource = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private OnClickDeleteButtonListener clickDeleteButtonListener;

    ListPopUpWindowAdapter(Activity OpeningScreenActivity, List<String> dataSource, @NonNull OnClickDeleteButtonListener clickDeleteButtonListener){
        this.OpeningScreenActivity = OpeningScreenActivity;
        this.mDataSource = dataSource;
        layoutInflater = OpeningScreenActivity.getLayoutInflater();
        this.clickDeleteButtonListener = clickDeleteButtonListener;
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public String getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.custom_layout_settings, null);
            holder.musicTitle = (TextView) convertView.findViewById(R.id.musicText);
            holder.soundTitle = (TextView) convertView.findViewById(R.id.soundText);
            holder.exitButton = (ImageView) convertView.findViewById(R.id.exitButton);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        // bind data
        holder.soundTitle.setText(getItem(position));
        holder.musicTitle.setText(getItem(position));
        holder.exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickDeleteButtonListener.onClickExitButton(position);
            }
        });
        return convertView;
    }

    public class ViewHolder{
        private TextView soundTitle;
        private TextView musicTitle;
        private ImageView exitButton;
    }

    // interface to return callback to activity
    public interface OnClickDeleteButtonListener{
        void onClickExitButton(int position);
    }
}
