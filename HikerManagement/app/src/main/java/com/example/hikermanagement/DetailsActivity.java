package com.example.hikermanagement;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_details);

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        String details = dbHelper.getDetails();
        TextView detailsTxt = (TextView) findViewById(R.id.detailsText);
        detailsTxt.setText(details);
    }
}
