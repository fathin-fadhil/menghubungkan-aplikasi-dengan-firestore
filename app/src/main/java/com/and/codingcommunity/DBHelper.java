package com.and.codingcommunity;

import static android.content.ContentValues.TAG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "siswasmk2yk.db";

    public static final String SISWA_TABLE_NAME = "siswa";

    public static final String SISWA_COLUMN_ID = "id";
    public static final String SISWA_COLUMN_NAME = "name";
    public static final String SISWA_COLUMN_ALAMAT= "alamat";
    public static final String SISWA_COLUMN_NOHP= "nohp";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table " + SISWA_TABLE_NAME  + " (id integer primary key, name text, alamat text, nohp text) "
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS siswa");
        onCreate(sqLiteDatabase);
    }

    public void insertSiswa(String name, String id, String alamat, String nohp) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("alamat", alamat);
        contentValues.put("nohp", nohp);

        db.insert("siswa", null, contentValues);
    }

    public ArrayList<Siswa> getAllSiswa() {

        ArrayList<Siswa> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select * from siswa", null);

        res.moveToFirst();
        while (res.isAfterLast() == false) {

            Siswa siswa = new Siswa(
                    res.getString(res.getColumnIndexOrThrow(SISWA_COLUMN_ID)),
                    res.getString(res.getColumnIndexOrThrow(SISWA_COLUMN_NAME)),
                    res.getString(res.getColumnIndexOrThrow(SISWA_COLUMN_ALAMAT)),
                    res.getString(res.getColumnIndexOrThrow(SISWA_COLUMN_NOHP)));
            Log.w(TAG,(res.getString(res.getColumnIndexOrThrow(SISWA_COLUMN_ALAMAT)) + "NO HP = " + res.getString(res.getColumnIndexOrThrow(SISWA_COLUMN_NOHP))));

            array_list.add(siswa);

            res.moveToNext();
        }
        close();
        return array_list;
    }

    public boolean updateData(String id, String nama, String alamat, String nohp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //String query = "UPDATE " + SISWA_TABLE_NAME + " SET " + SISWA_COLUMN_NAME + " = '" + nama + "' WHERE " + SISWA_COLUMN_ID + " = '" + id + "'";
        Log.w("query = ", "update");
        //db.execSQL(query);

        contentValues.put(SISWA_COLUMN_ID, id);
        contentValues.put(SISWA_COLUMN_NAME, nama);
        contentValues.put(SISWA_COLUMN_ALAMAT, alamat);
        contentValues.put(SISWA_COLUMN_NOHP, nohp);
        Log.w("update", contentValues.toString());
        db.update(SISWA_TABLE_NAME, contentValues, "id=?", new String[] {id});

        return true;
    }

    public boolean deleteData(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Log.w("Query "," data delete");
        db.delete(SISWA_TABLE_NAME, "id=?", new String[]{id});
        db.close();
        return true;
    }
}



