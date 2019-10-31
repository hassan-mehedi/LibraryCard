package com.example.librarycard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {

    UserDetails userDetails;
    DatabaseHelper databaseHelper;
    Button submit;
    String[] deptName;
    Spinner department;
    EditText firstName, lastName, studentId, emailAddress, phoneNumber, address, dob, pws;
    String firstNameText, deptText, lastNameText, studentIdText, emailAddressText, phoneNumberText, addressText, dobText, name, pwsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        department = findViewById(R.id.createDepartmentSpinnerId);
        submit = findViewById(R.id.createSubmitId);

        firstName = findViewById(R.id.createFirstNameId);
        lastName = findViewById(R.id.createLastNameId);
        studentId = findViewById(R.id.createStudentId);
        emailAddress = findViewById(R.id.createEmailId);
        phoneNumber = findViewById(R.id.createPhoneNumberId);
        address = findViewById(R.id.createAddressId);
        dob = findViewById(R.id.createDOBId);
        pws = findViewById(R.id.createPasswordId);

        userDetails = new UserDetails();
        databaseHelper = new DatabaseHelper(this);

        deptName = getResources().getStringArray(R.array.dept);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.spinner_layout,
                R.id.spinnerTextViewId,deptName);
        department.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstNameText = firstName.getText().toString();
                lastNameText = lastName.getText().toString();
                studentIdText = studentId.getText().toString();
                emailAddressText = emailAddress.getText().toString();
                phoneNumberText = phoneNumber.getText().toString();
                addressText = address.getText().toString();
                deptText = department.getSelectedItem().toString();
                dobText = dob.getText().toString();
                pwsText = pws.getText().toString();

                userDetails.setFirstName(firstNameText);
                userDetails.setLastName(lastNameText);
                userDetails.setId(studentIdText);
                userDetails.setDepartment(deptText);
                userDetails.setEmail(emailAddressText);
                userDetails.setPhone(phoneNumberText);
                userDetails.setAddress(addressText);
                userDetails.setDob(dobText);
                userDetails.setPws(pwsText);

                if (firstName.equals("") || lastNameText.equals("") || studentIdText.equals("") || emailAddressText.equals("") || phoneNumberText.equals("") || addressText.equals("") || deptText.equals("") || dobText.equals("") || pwsText.equals("")){

                    Toast.makeText(getApplicationContext(),"Fill every field",Toast.LENGTH_SHORT).show();

                }
                else {
                    long rowId = databaseHelper.insertData(userDetails);

                    if (rowId>0){

                        SharedPreferences sharedPreferences = getSharedPreferences(userDetails.getEmail(), Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString("firstname",userDetails.getFirstName());
                        editor.putString("lastname",userDetails.getLastName());
                        editor.putString("department",userDetails.getDepartment());
                        editor.putString("email",userDetails.getEmail());
                        editor.putString("phone",userDetails.getPhone());
                        editor.putString("address",userDetails.getAddress());
                        editor.putString("dob",userDetails.getDob());
                        editor.putString("id",userDetails.getId());
                        editor.commit();
                    }
                    Toast.makeText(getApplicationContext(),"Account is created, login to enter.",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(CreateAccount.this,StartPage.class);
                    startActivity(intent);
                }


            }
        });
    }
}
