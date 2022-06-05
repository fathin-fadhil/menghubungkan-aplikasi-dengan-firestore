package com.and.codingcommunity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class SiswaAdapter extends ArrayAdapter<Siswa> {

    private DBHelper mydb;
    public SiswaAdapter(@NonNull Context context,
                        int resource,
                        @NonNull ArrayList<Siswa> objects) {
        super(context, resource, objects);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        Siswa siswa = getItem(position);
        View view = convertView;
        if (view == null){
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_siswa, null);
        }

        TextView id = view.findViewById(R.id.id);
        TextView nama = view.findViewById(R.id.nama);

        Button editbtn = (Button)view.findViewById(R.id.edit_button);

        id.setText(siswa.getId());
        nama.setText(siswa.getNama());

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),id.getText().toString(), Toast.LENGTH_LONG).show();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Edit Nama");
                final EditText input = new EditText(getContext());
                builder.setView(input);
                input.setText(id.getText().toString());

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String editnama;
                        editnama = input.getText().toString();
                        Toast.makeText(getContext(),editnama, Toast.LENGTH_SHORT).show();
//                        mydb.updateData(id.getText().toString(), nama.getText().toString());
//                        notifyDataSetChanged();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        return view;
    }
}