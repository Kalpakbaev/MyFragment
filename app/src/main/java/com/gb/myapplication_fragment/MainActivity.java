package com.gb.myapplication_fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        Fragment backStackFragment = (Fragment)getSupportFragmentManager().findFragmentById(R.id.note);
        if (backStackFragment != null && backStackFragment instanceof ContentFragment){
            onBackPressed();
        }
    }
}