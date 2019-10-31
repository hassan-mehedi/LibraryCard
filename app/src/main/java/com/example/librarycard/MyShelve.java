package com.example.librarycard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.R.layout.simple_list_item_1;
import static com.example.librarycard.R.drawable.data_structure;
import static com.example.librarycard.R.drawable.integral_calculas_differential_equations;
import static com.example.librarycard.R.drawable.principles_of_analog_electronics;
import static com.example.librarycard.R.drawable.structured_programming_language;

public class MyShelve extends Fragment {

    String[] book_name;
    int[] book_image = new int[]{R.drawable.intruduction_to_computer_system, principles_of_analog_electronics,structured_programming_language,
    integral_calculas_differential_equations,data_structure};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_shelve,container,false);

        book_name = getResources().getStringArray(R.array.books_name);
        ListView listView = view.findViewById(R.id.shelveListViewId);
        listView.setAdapter(new CustomAdapter(getActivity(),book_name,book_image));

        return view;
    }
}
