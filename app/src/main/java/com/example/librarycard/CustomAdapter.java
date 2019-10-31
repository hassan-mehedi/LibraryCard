package com.example.librarycard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter {

    int[] book_image;
    String[] book_name;
    Context context;

    public CustomAdapter(Context context, String[] book_name, int[] books_image) {
        this.book_image = books_image;
        this.book_name = book_name;
        this.context = context;
    }

    @Override
    public int getCount() {
        return book_name.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.book_list_layout,parent,false);

        ImageView imageView = convertView.findViewById(R.id.bookListImageViewId);
        TextView textView = convertView.findViewById(R.id.bookListTextViewId);

        imageView.setImageResource(book_image[position]);
        textView.setText(book_name[position]);

        return convertView;
    }
}
