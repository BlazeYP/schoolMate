package com.example.pinkninja;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class StudentStatusDialogOpener extends AppCompatDialogFragment {
    private static RadioGroup studentStatus;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.student_status, null);
        studentStatus = view.findViewById(R.id.student_status);
        builder.setView(view).setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Assign", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int status = studentStatus.getCheckedRadioButtonId();
                switch (status) {
                    case R.id.new_registration:
                        Intent openActivity = new Intent("com.example.pinkninja.ENROLLMENT");
                        startActivity(openActivity);
                        break;
                    case R.id.enrolled:
                        openActivity = new Intent("com.example.pinkninja.SCROLLINGACTIVITY");
                        startActivity(openActivity);
                        break;
                }
            }
        });
        return builder.create();
    }
}
