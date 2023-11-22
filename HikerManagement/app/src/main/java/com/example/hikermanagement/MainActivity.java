package com.example.hikermanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button savebtn = (Button) findViewById(R.id.button);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetails();
            }
        });
    }

    private void saveDetails() {
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        EditText nameTxt = (EditText) findViewById(R.id.editTextText);
        EditText dobTxt = (EditText) findViewById(R.id.editTextText2);
        EditText emailTxt = (EditText) findViewById(R.id.editTextText3);

        String name = nameTxt.getText().toString();
        String dob = dobTxt.getText().toString();
        String email = emailTxt.getText().toString();

        long personId = dbHelper.insertDetails(name,dob,email);
        Toast.makeText(this, "Person has been created with id: " + personId, Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, DetailsActivity.class);
        startActivity(i);
    }
}