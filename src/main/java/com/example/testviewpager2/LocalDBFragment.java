package com.example.testviewpager2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class LocalDBFragment extends Fragment {
    private DataBaseHelper dataBaseHelper;
    Button addBtn;
    Button deleteBtn;
    TableLayout list;
    EditText nameEdit;
    EditText courseEdit;
    EditText idEdit;

    public LocalDBFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local_d_b,
                container, false);
        addBtn = (Button) view.findViewById(R.id.addBtn);
        deleteBtn = (Button) view.findViewById(R.id.deleteBtn);
        list = (TableLayout) view.findViewById(R.id.list);
        nameEdit = (EditText) view.findViewById(R.id.nameedit);
        courseEdit = (EditText) view.findViewById(R.id.courseedit);
        idEdit = (EditText) view.findViewById(R.id.deleteID);
        if(addBtn != null){
            addBtn.setOnClickListener(c -> onAddClick());
        }
        if(deleteBtn != null){
            deleteBtn.setOnClickListener(c -> onDeleteClick());
        }
        dataBaseHelper = new DataBaseHelper(getContext());
        LoadData();
        return view;
    }

    private void LoadData() {
        SQLiteDatabase db = dataBaseHelper.getReadableDatabase();
        Cursor cursor = db.query(DBModel.UserClass.TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            TextView text = new TextView(getContext());
            text.setText("Имя: " + cursor.getString(1) + " Курс: " + cursor.getInt(2) + " [" + cursor.getInt(0)+"]");
            TableRow tr = new TableRow(getContext());
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            tr.addView(text);
            list.addView(tr);
        }
        db.close();
    }

    private void AddData(String name, int course) {
        TextView text = new TextView(getContext());
        text.setText("Имя: " + name + " Курс: " + course);
        TableRow tr = new TableRow(getContext());
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
        tr.addView(text);
        list.addView(tr);
    }

    private void onAddClick() {
        if (nameEdit.getText() != null && courseEdit.getText() != null) {
            String name = nameEdit.getText().toString();
            int course = Integer.parseInt(courseEdit.getText().toString());
            ContentValues values = new ContentValues();
            values.put(DBModel.UserClass.COL_NAME, name);
            values.put(DBModel.UserClass.COL_COURSE, course);
            SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
            db.insert(DBModel.UserClass.TABLE_NAME, null, values);
            db.close();
            AddData(name, course);
        }
    }

    private void onDeleteClick() {
        if(idEdit.getText() != null && list != null){
            int id = Integer.parseInt(idEdit.getText().toString());
            ContentValues values = new ContentValues();
            values.put(DBModel.UserClass._ID, id);
            SQLiteDatabase db = dataBaseHelper.getWritableDatabase();
            db.delete(DBModel.UserClass.TABLE_NAME, "_id = ?", new String[]{Long.toString(id)} );
            db.close();
            list.removeAllViews();
            LoadData();
        }
    }
}