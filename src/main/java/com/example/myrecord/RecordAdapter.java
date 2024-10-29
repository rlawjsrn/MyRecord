package com.example.myrecord;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecordAdapter extends BaseAdapter {
    ArrayList<RecordVO> data;
    ArrayList<RecordVO> displayListItem;
    public RecordAdapter() {
    }

    public RecordAdapter(ArrayList<RecordVO> data) {
        this.data = data;
    }

    public int getCount() {return data.size();}

    public Object getItem(int i) {return data.get(i);}

    public long getItemId(int i) {return i;}

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        view = inflater.inflate(R.layout.listview_item, viewGroup, false);
        ImageView imgMy = view.findViewById(R.id.imgMy1);
        TextView txtMyTitle = view.findViewById(R.id.txtMyTitle);
        TextView txtMyPrice = view.findViewById(R.id.txtMyAuthor1);
        TextView txtMyAuthor = view.findViewById(R.id.txtMyPrice1);
        txtMyTitle.setText(data.get(i).getTitle());
        txtMyPrice.setText(data.get(i).getPriceStandard());
        txtMyAuthor.setText(data.get(i).getAuthor());

        Glide.with(imgMy.getContext()).load(data.get(i).getCoverSmallUrl()).into(imgMy);
        return view;
    }

}
