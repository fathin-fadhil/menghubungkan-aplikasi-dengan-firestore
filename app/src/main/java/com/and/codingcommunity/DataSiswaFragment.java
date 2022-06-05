package com.and.codingcommunity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class DataSiswaFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_item_siswa, container, false);
        return view;
    }

    Button editBtn;
    TextView nama;
    TextView id;
    private DBHelper mydb;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState){
        mydb = new DBHelper(getActivity());
        editBtn = view.findViewById(R.id.edit_button);
        nama = view.findViewById(R.id.nama);
        id = view.findViewById(R.id.id);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Edit Nama");
                final EditText input = new EditText(view.getContext());
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String editnama;
                        editnama = input.getText().toString();

                        mydb.updateData(id.getText().toString(), nama.getText().toString());

                        FragmentManager fm = getActivity().getSupportFragmentManager();
                        ListSiswaFragment fragm = (ListSiswaFragment)
                                fm.findFragmentById(R.id.fragment_list_siswa);
                        fragm.refreshList();
                    }

                });
            }
        });
    }

}