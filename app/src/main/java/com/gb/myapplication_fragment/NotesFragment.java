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

    public static final String CURRENT_NOTE = "note_current";
    private Note currentNote;

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
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(CURRENT_NOTE,currentNote);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null){
            currentNote = savedInstanceState.getParcelable(CURRENT_NOTE);
        }else {
            currentNote = new Note(0);
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            showLand();
        }
                   initView(view);
    }

    private void initView(View view) {
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
                    currentNote = new Note(FinalI);
                    if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                        showLand();
                    }else { //portland
                        showPort();
                    }
                }
            });
        }
    }

    private void showLand(){
        ContentFragment contentFragment = ContentFragment.newInstance(currentNote);
        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content,contentFragment).commit();
    }
    private void showPort(){
        ContentFragment contentFragment = ContentFragment.newInstance(currentNote);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.note,contentFragment).addToBackStack("").commit();
    }
}