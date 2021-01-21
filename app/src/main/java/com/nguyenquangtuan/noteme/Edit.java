package com.nguyenquangtuan.noteme;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.nguyenquangtuan.noteme.R;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Edit extends AppCompatActivity {
    Toolbar toolbar;
    EditText nTitle, nContent;
    Calendar c;
    Spinner spinner;
    String todaysDate;
    String currentTime;
    long nId;
    String s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent i = getIntent();
        nId = i.getLongExtra("ID", 0);
        SimpleDatabase db = new SimpleDatabase(this);
        Note note = db.getNote(nId);

        final String title = note.getTitle();
        String content = note.getContent();
        nTitle = findViewById(R.id.noteTitle);
        nContent = findViewById(R.id.noteDetails);
        String subject = note.getSubject();
        nTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    getSupportActionBar().setTitle(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        spinner.se
        nTitle.setText(title);
        nContent.setText(content);
        spinner = findViewById(R.id.spinner);
        final List<String> spinnerList = new ArrayList<>();
        spinnerList.add("Gia dinh");
        spinnerList.add("Cong viec");
        spinnerList.add("Chi tieu");
        spinnerList.add("Tai khoan");
        spinnerList.add("Ban be");
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter(getApplicationContext(),spinnerList);
        spinner.setAdapter(spinnerAdapter);
        String s2 = subject.replaceAll("\\s", "");
        for (int j = 0;j<spinnerList.size();j++){

            String s1 = spinnerList.get(j).replaceAll("\\s", "");
            if(s1.equals(s2)) {
                spinner.setSelection(j);
            }
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                s = spinnerList.get(i);
                Log.i("MESSAGE",s);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // set current date and time
        c = Calendar.getInstance();
        todaysDate = c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DAY_OF_MONTH);
        Log.d("DATE", "Date: " + todaysDate);
        currentTime = pad(c.get(Calendar.HOUR)) + ":" + pad(c.get(Calendar.MINUTE));
        Log.d("TIME", "Time: " + currentTime);
    }


    private String pad(int time) {
        if (time < 10)
            return "0" + time;
        return String.valueOf(time);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {


//            Log.d("EDITED", "edited: before saving id -> " + note.getId());
            SimpleDatabase sDB = new SimpleDatabase(getApplicationContext());
            Note note1 = sDB.getNote(nId);
            Log.i("Note1"," "+note1.toString());

            Note note = new Note(nId, nTitle.getText().toString(), nContent.getText().toString(),note1.getCreatedDate(),note1.getCreatedTime(), todaysDate, currentTime,s);
            Log.i("Note"," "+note.toString());
            long id = sDB.editNote(note);
            Log.d("EDITED", "EDIT: id " + id);
            goToMain();
            Toast.makeText(this, "Note Edited.", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.delete) {
            Toast.makeText(this, "Canceled", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


}
