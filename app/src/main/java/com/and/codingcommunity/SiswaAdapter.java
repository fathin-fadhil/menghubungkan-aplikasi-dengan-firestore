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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;


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
        TextView alamat = view.findViewById(R.id.alamat);
        TextView nohp = view.findViewById(R.id.nohp);

        Button editbtn = (Button)view.findViewById(R.id.edit_button);
        Button deletebtn = (Button)view.findViewById(R.id.delete_button);
        mydb = new DBHelper(view.getContext());

        id.setText(siswa.getId());
        nama.setText(siswa.getNama());
        alamat.setText(siswa.getAlamat());
        nohp.setText(siswa.getNohp());

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Edit Data");

                final EditText input = new EditText(getContext());
                final EditText inpAlamat = new EditText(getContext());
                final EditText inpNohp = new EditText(getContext());

                builder.setView(input);
                builder.setView(inpAlamat);
                builder.setView(inpNohp);

                LinearLayout layoutdialog = new LinearLayout(getContext());
                layoutdialog.setOrientation(LinearLayout.VERTICAL);;
                layoutdialog.addView(input);
                layoutdialog.addView(inpAlamat);
                layoutdialog.addView(inpNohp);
                builder.setView(layoutdialog);


                input.setText(nama.getText().toString().trim());
                inpAlamat.setText(alamat.getText().toString().trim());
                inpNohp.setText(nohp.getText().toString().trim());

                builder.setPositiveButton("SIMPAN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String editnama = input.getText().toString().trim();
                        String editalamat = inpAlamat.getText().toString().trim();
                        String editnohp = inpNohp.getText().toString().trim();

                        Toast.makeText(getContext(),editnama, Toast.LENGTH_SHORT).show();

                        boolean isUpdate = mydb.updateData(id.getText().toString().trim(), editnama, editalamat, editnohp);
                        if(isUpdate) {
                            notifyDataSetChanged(); //<-- useless piece of shiy
                            Toast.makeText(getContext(),"Data Tersimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(),"Gagal Menyimpan Data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Confirm Deletion");
                builder.setMessage("Hapus Data?");
                builder.setPositiveButton("HAPUS", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        boolean isDeleted = mydb.deleteData(id.getText().toString().trim());
                        if(isDeleted) {
                            notifyDataSetChanged(); //<-- useless piece of shiy
                            Toast.makeText(getContext(),"Data Terhapus", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(),"Gagal Menghapus Data", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("BATAL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        dialogInterface.cancel();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        return view;
    }
}