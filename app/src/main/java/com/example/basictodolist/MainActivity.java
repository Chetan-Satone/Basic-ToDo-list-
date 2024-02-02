package com.example.basictodolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button add_button;
    EditText new_item;
    RecyclerView recyclerView;

    ListAdapter adapter;

    ArrayList<String> ToDoListItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_button = findViewById(R.id.btAdd);
        new_item = findViewById(R.id.edtnewitem);

        ToDoListItems = FileHelper.readData(this);


        recyclerView = findViewById(R.id.recyclerview);
        adapter = new ListAdapter(this,ToDoListItems);
        LinearLayoutManager LinearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LinearLayoutManager);
        recyclerView.setAdapter(adapter);


        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String new_item_string = new_item.getText().toString();

                ToDoListItems.add(new_item_string);
                adapter.notifyItemChanged(0);
                new_item.setText("");

                FileHelper.writeData(ToDoListItems, getBaseContext());

            }
        });

    }
}