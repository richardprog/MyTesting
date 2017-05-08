package com.example.exoest.mytesting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DogActivity extends AppCompatActivity {
    TextView dogActivityTextView;
    EditText dogActivityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dog);

        dogActivityEditText = (EditText) findViewById(R.id.dogActivityEditText);
        Bundle prevIntentData= getIntent().getExtras();
        if (prevIntentData == null){
            return;
        }
        dogActivityTextView = (TextView) findViewById(R.id.dogActivityTextView);
        dogActivityTextView.setText("testing...");
    }

    public void onClickReturnInput(View view){
        String input = dogActivityEditText.getText().toString();

        Intent i = new Intent();
        i.putExtra("fromDog", input);
        setResult(RESULT_OK, i);

        finish();
    }
}
