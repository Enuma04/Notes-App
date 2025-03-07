package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class WriteActivity extends AppCompatActivity {

    ArrayList<String> notes;
    EditText write;
    TextView limit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);


        write = findViewById(R.id.write);
        limit = findViewById(R.id.limit);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", -1);
        notes = PreferenceHelper.getPref(this);
        //Log.i("you have this:", PreferenceHelper.getPref(this).get(0) + "at index:" + index);
        if(index != -1){
            String note = notes.get(index);
            write.setText(note);
            updateLimit(note);
        }

        write.addTextChangedListener(new TextWatcher() {
            int ind = index;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateLimit(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    notes.set(ind, s.toString()); // Update note in ArrayList
                    PreferenceHelper.savePref(WriteActivity.this, notes);

                }catch(IndexOutOfBoundsException e){
                    notes.add(s.toString());
                    ind = notes.size() - 1;
                    PreferenceHelper.savePref(WriteActivity.this, notes);
                }
            }
        });
    }

    public void updateLimit(String s){
        int len = s.length();
        String text = String.format("%d/50", 50 - len);
        limit.setText(text);
    }

}