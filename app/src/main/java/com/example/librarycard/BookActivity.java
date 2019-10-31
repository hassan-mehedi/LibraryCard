package com.example.librarycard;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class BookActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView, stockText;
    private Button book, borrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        imageView = findViewById(R.id.selectedBookListImageId);
        textView = findViewById(R.id.selectedBookListTextId);
        book = findViewById(R.id.bookButtonId);
        borrow = findViewById(R.id.borrowButtonId);
        stockText = findViewById(R.id.stockId);

        SharedPreferences sharedPreferences = getSharedPreferences("book__access",MODE_PRIVATE);
        String val = sharedPreferences.getString("book_name","No name found");

            if (val.equals("Compilers Principles, Techniques and Tools by Alfred V Aho")){
                textView.setText("Compilers Principles, Techniques and Tools by Alfred V Aho");
                imageView.setImageResource(R.drawable.compilers_principle);
            }
            if (val.equals("Principles of Compiler Design by Alfred V Aho")){
                textView.setText("Principles of Compiler Design by Alfred V Aho");
                imageView.setImageResource(R.drawable.compiler_design);
            }
            if (val.equals("Introduction to Computers by Peter Norton")){
                textView.setText("Introduction to Computers by Peter Norton");
                imageView.setImageResource(R.drawable.intro_comp);
            }
            if (val.equals("Computer Fundamentals by M Lutfar Rahman")){
                textView.setText("Computer Fundamentals by M Lutfar Rahman");
                imageView.setImageResource(R.drawable.com_fun);
            }
            if (val.equals("System Analysis and Design: A Practioners Approach by Kenneth E Kendall")){
                textView.setText("System Analysis and Design: A Practioners Approach by Kenneth E Kendall");
                imageView.setImageResource(R.drawable.sys_des);
            }
            if (val.equals("System Analysis and Design by Elias M Awad")){
                textView.setText("System Analysis and Design by Elias M Awad");
                imageView.setImageResource(R.drawable.sys_ana);
            }
            if (val.equals("Computer System Architecture by M Morris Mano")){
                textView.setText("Computer System Architecture by M Morris Mano");
                imageView.setImageResource(R.drawable.com_arch);
            }
            if (val.equals("Fundamentals of Computer Algorithms by Ellis Horowitz")){
                textView.setText("Fundamentals of Computer Algorithms by Ellis Horowitz");
                imageView.setImageResource(R.drawable.com_algo);
            }
            if (val.equals("Microprocessor Data Handbook by BPB Publications")){
                textView.setText("Microprocessor Data Handbook by BPB Publications");
                imageView.setImageResource(R.drawable.mic_data);
            }
            if (val.equals("Microprocessor and Microcomputer by based System Design by Md Rafiquzzaman")){
                textView.setText("Microprocessor and Microcomputer by based System Design by Md Rafiquzzaman");
                imageView.setImageResource(R.drawable.mic_bas);

        }

        Random random = new Random();

            int change = random.nextInt(100);
            int stock = random.nextInt(7);
            String st = Integer.toString(stock);

            stockText.setText(st);

            book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    book.setBackgroundDrawable(getResources().getDrawable(R.drawable.book_borrow));
                    book.setText("Booked");
                }
            });

            if (change%2 == 0){
                if (stock!=0){
                    borrow.setBackgroundDrawable(getResources().getDrawable(R.drawable.book_borrow));
                    borrow.setText("Borrowed");
                }
                else {
                    borrow.setBackgroundDrawable(getResources().getDrawable(R.drawable.book_borrow));
                    borrow.setText("Not available");
                }
            }
            else if (change%2 == 1){
                if (stock!=0){
                    borrow.setBackgroundDrawable(getResources().getDrawable(R.drawable.round_button));
                    borrow.setText("Not borrowed");
                }
                else {
                    borrow.setBackgroundDrawable(getResources().getDrawable(R.drawable.book_borrow));
                    borrow.setText("Not available");
                }
            }

    }
}
