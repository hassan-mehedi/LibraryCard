package com.example.librarycard;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "accountinfo.db";
    private static final String TABLE_NAME = "user_info";
    private static final String ID = "Student_id";
    private static final String FIRST_NAME = "First_name";
    private static final String LAST_NAME = "Last_name";
    private static final String DEPARTMENT = "Department";
    private static final String EMAIL = "Email_address";
    private static final String PHONE = "Phone_number";
    private static final String ADDRESS = "Address";
    private static final String DATE_OF_BIRTH = "DOB";
    private static final String PASSWORD = "Password";
    private static final int version = 4;
    private Context context;
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY, "+FIRST_NAME+" VARCHAR(20), "+LAST_NAME+" VARCHAR(20), "+DEPARTMENT+" VARCHAR(10), "+EMAIL+" VARCHAR(50) UNIQUE, "+PHONE+" INTEGER(20), "+ADDRESS+" VARCHAR(255), "+DATE_OF_BIRTH+" VARCHAR(255), "+PASSWORD+" VARCHAR(255));";
    private static final String DROP_TABLE = " DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE);
            Toast.makeText(context,"Oncreate method is called",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context,"Exception: "+e,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
            Toast.makeText(context,"onUpgrade method is called",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(context,"Exception: "+e,Toast.LENGTH_SHORT).show();
        }

    }

    public long insertData(UserDetails userDetails){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ID,userDetails.getId());
        contentValues.put(FIRST_NAME,userDetails.getFirstName());
        contentValues.put(LAST_NAME,userDetails.getLastName());
        contentValues.put(DEPARTMENT,userDetails.getDepartment());
        contentValues.put(EMAIL,userDetails.getEmail());
        contentValues.put(PHONE,userDetails.getPhone());
        contentValues.put(ADDRESS,userDetails.getAddress());
        contentValues.put(DATE_OF_BIRTH,userDetails.getDob());
        contentValues.put(PASSWORD,userDetails.getPws());

        long rowId = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowId;

    }

    public Boolean matchPassword(String email, String password){

        Boolean result = false;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.getCount() == 0){

        }
        else{
            while (cursor.moveToNext()){
                String emailText = cursor.getString(4);
                String passText = cursor.getString(8);

                if (emailText.equals(email) && passText.equals(password)){
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

}
