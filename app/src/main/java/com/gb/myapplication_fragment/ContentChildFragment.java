package com.gb.myapplication_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ContentChildFragment extends Fragment {

    public static final String ARG_KEY = "note";

    private Note note;

    public static ContentChildFragment newInstance(Note note) {
        ContentChildFragment fragment = new ContentChildFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_KEY, note);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contents_child, container, false);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_content_fragment, menu);
        menu.findItem(R.id.action_about).setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_toast: {
                Toast.makeText(requireContext(), "Toast", Toast.LENGTH_LONG).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
        public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState){
            super.onViewCreated(view, savedInstanceState);
            note = getArguments().getParcelable(ARG_KEY);
            String[] contents = getResources().getStringArray(R.array.contents);
            String[] date = getResources().getStringArray(R.array.date);
            TextView textView = new TextView(getContext());
            TextView textViewOne = new TextView(getContext());
            String dateName = date[note.getIndex()];
            String contentName = contents[note.getIndex()];
            textView.setTextSize(30f);
            textViewOne.setTextSize(25f);
            textViewOne.setText(dateName);
            textView.setText(contentName);
            ((LinearLayout) view).addView(textViewOne);
            ((LinearLayout) view).addView(textView);

            view.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getParentFragmentManager().popBackStack();
                }
            });
            textView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    PopupMenu popupMenu = new PopupMenu(requireContext(), view);
                    requireActivity().getMenuInflater().inflate(R.menu.popup, popupMenu.getMenu());
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId()){
                                case R.id.action_popup_clear:{
                                   textView.setVisibility(View.GONE);
                                    return true;
                                }
                                case R.id.action_popup_exit:{
                                    requireActivity().finish();
                                    return true;
                                }
                            }
                             return false;
                        }
                    });
                    popupMenu.show();
                    return false;
                }
            });
        }
}