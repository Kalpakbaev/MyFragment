package com.gb.myapplication_fragment;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class NotesFragment extends Fragment {


    public static NotesFragment newInstance() {
        NotesFragment fragment = new NotesFragment();
        Bundle bundle = new Bundle();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] notes = getResources().getStringArray(R.array.notes);
        for (int i = 0; i < notes.length; i++){
            String notesName = notes[i];
            TextView textView = new TextView(getContext());
            textView.setTextSize(30f);
            textView.setText(notesName);
            ((LinearLayout) view).addView(textView);
           final int FinalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Note Note = new Note(FinalI);
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        ContentFragment contentFragment = ContentFragment.newInstance(Note);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content,contentFragment).commit();
                    }else { //portland
                        ContentFragment contentFragment = ContentFragment.newInstance(Note);
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.note,contentFragment).addToBackStack("").commit();


                    }
                }
            });
        }
    }
}