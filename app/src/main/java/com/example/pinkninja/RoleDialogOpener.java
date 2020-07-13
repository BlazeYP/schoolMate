package com.example.pinkninja;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class RoleDialogOpener extends AppCompatDialogFragment {
    private static RadioGroup role;
    private  RoleDialogListner listner;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_layout,null);
        role = view.findViewById(R.id.role);
        builder.setView(view).setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("Assign", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String roleplay="no role";
                int radio = role.getCheckedRadioButtonId();
                switch (radio){
                    case R.id.schoolOwner:
                        roleplay = "School Owner";
                        break;
                    case R.id.teacher:
                        roleplay = "Teacher";
                        break;
                    case R.id.parent:
                        roleplay = "Parent";
                        break;
                    case R.id.worker:
                        roleplay = "Worker";
                        break;
                    case R.id.student:
                        roleplay = "Student";
                }
                listner.getSelectedRole(roleplay);
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listner = (RoleDialogListner) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Must Implement ExampleDialogListner!");
        }
    }

    public interface RoleDialogListner{
        void getSelectedRole(String role);
    }
}
