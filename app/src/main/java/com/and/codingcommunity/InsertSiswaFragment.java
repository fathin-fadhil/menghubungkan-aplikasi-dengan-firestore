package com.and.codingcommunity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class InsertSiswaFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.insert_fragment, container, false);
        return view;
    }

    private DBHelper mydb;
    Button btnSave;
    EditText editTextNama;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mydb = new DBHelper(getActivity());
        btnSave = view.findViewById(R.id.btn_save);
        editTextNama = view.findViewById(R.id.etext_nama);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(editTextNama.getText().toString())) {
                    editTextNama.setError("Data Kosong");
                } else {
                    mydb.insertSiswa(editTextNama.getText().toString());

                    FragmentManager fm = getActivity().getSupportFragmentManager();
                    ListSiswaFragment fragm = (ListSiswaFragment)
                            fm.findFragmentById(R.id.fragment_list_siswa);
                    fragm.refreshList();
                }

            }
        });
    }

}