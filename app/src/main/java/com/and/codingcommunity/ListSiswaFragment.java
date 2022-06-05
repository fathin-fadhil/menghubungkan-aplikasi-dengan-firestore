package com.and.codingcommunity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import java.util.ArrayList;

public class ListSiswaFragment extends Fragment {


    private SiswaAdapter siswaAdapter;
    private ListView listSiswa;

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
        mydb = new DBHelper(getActivity());

        ArrayList arrayList = mydb.getAllSiswa();
        listSiswa = view.findViewById(R.id.list_siswa);

        siswaAdapter = new SiswaAdapter(getActivity(),0, arrayList);
        listSiswa.setAdapter(siswaAdapter);
    }

    public void refreshList() {
        Log.d(ListSiswaFragment.class.getName(), "refreshList: clicked");
        ArrayList array_list = mydb.getAllSiswa();
        siswaAdapter.clear();
        siswaAdapter.addAll(array_list);
        siswaAdapter.notifyDataSetChanged();
    }


}
