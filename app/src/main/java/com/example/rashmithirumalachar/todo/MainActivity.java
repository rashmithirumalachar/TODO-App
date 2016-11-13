package com.example.rashmithirumalachar.todo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import android.widget.Button;
import android.widget.TextView;




public class MainActivity extends AppCompatActivity {

    public static final int EDIT_ITEM_REQUEST = 1;
    List<String> items = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView todoList = (ListView) findViewById(R.id.todoList);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences("com.example.rashmithirumalachar.todo", MODE_PRIVATE);
        Set<String>  todolist= new HashSet<String>();


        preferences.getStringSet("TODOList", todolist);

        items.addAll(todolist);

        final ArrayAdapter<String> arrayItems = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, items);

        todoList.setAdapter(arrayItems);

        Button addButton = (Button) findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText itemInfo = (EditText) findViewById(R.id.editText);

                arrayItems.add(itemInfo.getText().toString());
                itemInfo.setText(" ");
                itemInfo.setHint("Add a new iteam");

            }
        });


        todoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent editIntent = new Intent(getApplicationContext(), EditItemActivity.class);
                editIntent.putExtra("Value", arrayItems.getItem(i));
                editIntent.putExtra("Position", i);
                startActivityForResult(editIntent, EDIT_ITEM_REQUEST);


            }
        });

        todoList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                arrayItems.remove(arrayItems.getItem(i));

                return true;
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == EDIT_ITEM_REQUEST) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {

                ListView todoList = (ListView) findViewById(R.id.todoList);

                TextView editText = (TextView) todoList.getChildAt(data.getIntExtra("Position", 0));

                editText.setText(data.getStringExtra("Value"));


            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("com.example.rashmithirumalachar.todo", MODE_PRIVATE);

        Set<String>  todolist= new HashSet<String>();
        todolist.addAll(items);
        preferences.edit().putStringSet("TODOList", todolist);
        preferences.edit().apply();
        preferences.edit().commit();


    }

    @Override
    protected void onDestroy() {
        super.onPause();

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("com.example.rashmithirumalachar.todo", MODE_PRIVATE);

        Set<String>  todolist= new HashSet<String>();
        todolist.addAll(items);
        preferences.edit().putStringSet("TODOList", todolist);
        preferences.edit().apply();
        preferences.edit().commit();


    }



}
