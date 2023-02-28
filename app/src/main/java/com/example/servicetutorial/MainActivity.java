package com.example.servicetutorial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText text;
    private Button start;
    private Button stop;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (EditText) findViewById(R.id.text);
        start = (Button) findViewById(R.id.start);
        stop = (Button) findViewById(R.id.stop);

        start.setOnClickListener(v -> {
            Intent intent = new Intent(this, MyService.class);
            intent.putExtra("text", text.getText().toString());
            startService(intent);
        });

        stop.setOnClickListener(v -> {
            stopService(new Intent(this, MyService.class));
        });

    }
}