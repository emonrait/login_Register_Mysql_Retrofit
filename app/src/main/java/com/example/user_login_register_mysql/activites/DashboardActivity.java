package com.example.user_login_register_mysql.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import android.os.Bundle;
import com.example.user_login_register_mysql.R;

public class DashboardActivity extends AppCompatActivity {
    private AppCompatTextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        textViewName = findViewById(R.id.textViewName);

        String name = getIntent().getStringExtra("name");
        textViewName.setText(name);


    }
}