package com.gb.myapplication_fragment;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

public class AboutFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_abaut, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),onBackPressedCallback);
        super.onViewCreated(view, savedInstanceState);
        InitView(view);
    }
    public void InitView(View view){
        view.findViewById(R.id.BtnAdd).setOnClickListener(view1 -> addNote());
    }
    public void addNote(){
        NotificationManager notificationManager =(NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("1",   "VERY_VERY_IMPORTANT_CHANNEL", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Канал для важных сообщений!");
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Notification notification = new NotificationCompat.Builder(requireContext(),"1")
                .setContentTitle("Внимание! Очень важное сообщение!")
                .setContentText("Была добаленна новая заметка")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build();

        notificationManager.notify(1, notification);


        Toast.makeText(requireContext(), "Типо добавил заметку", Toast.LENGTH_SHORT).show();
        requireActivity().onBackPressed();
    }

    }


