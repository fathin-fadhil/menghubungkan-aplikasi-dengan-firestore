package com.and.codingcommunity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;

public class ListSiswaFragment extends Fragment {


    SiswaAdapter siswaAdapter;
    ListView listSiswa;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstance) {
        View view = inflater.inflate(R.layout.list_siswa_fragment,
                container, false);
        return view;
    }

    private DBHelper mydb;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        /*mydb = new DBHelper(getActivity());

        ArrayList arrayList = mydb.getAllSiswa();
         */


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        listSiswa = view.findViewById(R.id.list_siswa);
        ArrayList<Siswa> arrayList = new ArrayList<>();

        db.collection("siswa")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Siswa siswa = new Siswa(document.getString("id"),
                                                        document.getString("nama"),
                                                        document.getString("alamat"),
                                                        document.getString("nohp"));
                                arrayList.add(siswa);
                                Log.e("aaaaa", siswa.getNama());
                            }

                            siswaAdapter = new SiswaAdapter(getContext(),0, arrayList);
                            listSiswa.setAdapter(siswaAdapter);
                            siswaAdapter.notifyDataSetChanged();

                        } else {
                            Log.w("TAG", "Error getting documents", task.getException());
                        }
                    }
                });


        //auto update every 1 second because  i canr figure out how to refresh listview from inside the edit button in the adapter bruhhhhhhhhhhhh
        final Handler handler = new Handler();
        handler.postDelayed( new Runnable() {

            @Override
            public void run() {
                refreshList();
                handler.postDelayed( this, 1000 );
            }
        }, 1000 );


    }

    public void refreshList() {
        /*ArrayList array_list = mydb.getAllSiswa();
        siswaAdapter.clear();
        siswaAdapter.addAll(array_list);
        siswaAdapter.notifyDataSetChanged();*/

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        ArrayList<Siswa> arrayList = new ArrayList<>();

        db.collection("siswa")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Siswa siswa = new Siswa(document.getString("id"),
                                        document.getString("nama"),
                                        document.getString("alamat"),
                                        document.getString("nohp"));
                                arrayList.add(siswa);
                            }

                            siswaAdapter.clear();
                            siswaAdapter.addAll(arrayList);
                            siswaAdapter.notifyDataSetChanged();
                            Log.w("TAG", "refreshing table");
                        } else {
                            Log.w("TAG", "Error getting documents", task.getException());
                        }
                    }
                });
    }
}
