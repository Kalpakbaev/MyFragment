package com.gb.myapplication_fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null){
            NotesFragment notesFragment = NotesFragment.newInstance();
           getSupportFragmentManager().beginTransaction().replace(R.id.note,notesFragment).commit();
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            Note defaultNote = new Note(R.id.content);
            ContentFragment contentFragment = ContentFragment.newInstance(defaultNote);
            getSupportFragmentManager().beginTransaction().replace(R.id.content,contentFragment).commit();
        }

    }
}