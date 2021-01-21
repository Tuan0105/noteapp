package com.nguyenquangtuan.noteme;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class SpinnerAdapter implements android.widget.SpinnerAdapter {
    private Context context;
    private List<String> spinner;

    public SpinnerAdapter(Context context, List<String> spinner) {
        this.context = context;
        this.spinner = spinner;
    }

    @Override
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.row,viewGroup,false);
        TextView tvInfo;
        tvInfo = view.findViewById(R.id.tvInfo);
        tvInfo.setText(spinner.get(i));
        return view;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getCount() {
        return spinner.size();
    }

    @Override
    public Object getItem(int i) {
        return spinner.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.row,viewGroup,false);
        TextView tvInfo;
        tvInfo = view.findViewById(R.id.tvInfo);
        tvInfo.setText(spinner.get(i));
        return view;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
