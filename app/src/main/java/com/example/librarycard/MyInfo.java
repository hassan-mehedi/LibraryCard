package com.example.librarycard;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MyInfo extends Fragment {



    TextView name, department, id, dob, phoneNumber, emailAddress, address, infoText;
    String firstName, lastName, nameText, deptText, studentIdText, emailAddressText, phoneNumberText, addressText, dobText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_info, container, false);
        UserDetails userDetails = new UserDetails();

        name = view.findViewById(R.id.nameId);
        department = view.findViewById(R.id.departmentId);
        id = view.findViewById(R.id.infoStudentId);
        dob = view.findViewById(R.id.dobId);
        phoneNumber = view.findViewById(R.id.infoPhoneNumberId);
        emailAddress = view.findViewById(R.id.infoEmailAddressId);
        address = view.findViewById(R.id.infoAddressId);
        infoText = view.findViewById(R.id.infoTextViewId);

        SharedPreferences sh = this.getActivity().getSharedPreferences("user",Context.MODE_PRIVATE);
        String bd = sh.getString("email","no value found");

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(bd, Context.MODE_PRIVATE);
        firstName = sharedPreferences.getString("firstname","No name found");
        lastName = sharedPreferences.getString("lastname","No name found");
        deptText = sharedPreferences.getString("department","No department found");
        studentIdText = sharedPreferences.getString("id","No id found");
        emailAddressText = sharedPreferences.getString("email","No email found");
        phoneNumberText = sharedPreferences.getString("phone","No phone number found");
        addressText = sharedPreferences.getString("address","No address found");
        dobText = sharedPreferences.getString("dob","No dob found");

        nameText = firstName + " " + lastName;

        infoText.setText(nameText);
        name.setText(nameText);
        id.setText(studentIdText);
        department.setText(deptText);
        emailAddress.setText(emailAddressText);
        phoneNumber.setText(phoneNumberText);
        address.setText(addressText);
        dob.setText(dobText);

        return view;
    }
}
