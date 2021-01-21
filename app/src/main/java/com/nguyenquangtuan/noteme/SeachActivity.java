package com.nguyenquangtuan.noteme;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class SeachActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SimpleDatabase simpleDatabase;
    EditText editText;
    Adapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        editText = findViewById(R.id.et_search);
        simpleDatabase = new SimpleDatabase(this);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    List<Note> allNotes = simpleDatabase.searchNote(editText.getText().toString());
                    recyclerView = findViewById(R.id.allNotes);
                    displayList(allNotes);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }
    private void displayList(List<Note> allNotes) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, allNotes);
        recyclerView.setAdapter(adapter);
    }
}
