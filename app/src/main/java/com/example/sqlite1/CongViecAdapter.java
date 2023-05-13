package com.example.sqlite1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CongViecAdapter extends BaseAdapter {
    private MainActivity context;
    private int layout;
    private List<CongViec> congViecList;

    public CongViecAdapter(MainActivity context, int layout, List<CongViec> congViecList) {
        this.context = context;
        this.layout = layout;
        this.congViecList = congViecList;
    }

    @Override
    public int getCount() {
        return congViecList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class viewHolder
    {
        TextView textViewTen;
        ImageView buttonxoa, buttonsua;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       viewHolder holder;
       if(convertView == null)
       {
           holder = new viewHolder();
           LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = inflater.inflate(layout,null);
           holder.textViewTen = (TextView) convertView.findViewById(R.id.id_textviewten);
           holder.buttonsua = (ImageView) convertView.findViewById(R.id.id_edit);
           holder.buttonxoa  = (ImageView) convertView.findViewById(R.id.id_delete);
           convertView.setTag(holder);
       }
       else
       {
           holder = (viewHolder) convertView.getTag();
       }
       CongViec congViec = congViecList.get(position);
       holder.textViewTen.setText(congViec.getTenTask());

       holder.buttonsua.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               context.DialogCapNhat(congViec.getTenTask(),congViec.getId());
           }
       });
       holder.buttonxoa.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
              context.DialogDelete(congViec.getTenTask(),congViec.getId());
           }
       });
        return convertView;
    }
}
