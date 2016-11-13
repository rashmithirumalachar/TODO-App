package com.example.rashmithirumalachar.todo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        Intent intent = getIntent();
        String edittext= intent.getStringExtra("Value");
        final int position = intent.getIntExtra("Position" , 0);


        EditText editItem =(EditText)findViewById(R.id.editTextItem);
        editItem.setText(edittext);

        Button saveButton=(Button)findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText editItem =(EditText)findViewById(R.id.editTextItem);

                Intent saveIntent= new Intent();
                saveIntent.putExtra("Value",editItem.getText().toString());
                saveIntent.putExtra("Position", position);
                setResult(Activity.RESULT_OK, saveIntent);
                finish();


            }
        });







    }
}
