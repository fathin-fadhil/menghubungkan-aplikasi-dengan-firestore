package com.and.codingcommunity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class SiswaAdapter extends ArrayAdapter<Siswa> {

    public SiswaAdapter(@NonNull Context context,
                        int resource,
                        @NonNull ArrayList<Siswa> objects) {
        super(context, resource, objects);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        Siswa siswa = getItem(position);
        if (convertView==null){
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item_siswa, parent, false);
        }
        TextView id = convertView.findViewById(R.id.id);
        TextView nama = convertView.findViewById(R.id.nama);

        id.setText(siswa.getId());
        nama.setText(siswa.getNama());

        return convertView;
    }
}