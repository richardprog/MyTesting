package com.example.exoest.mytesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RetrievedDetailsActivity extends AppCompatActivity {
    TextView textViewDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieved_details);

        Bundle userDetailsExtras = getIntent().getExtras();
        if (userDetailsExtras == null){
            return;
        }

        textViewDetails = (TextView) findViewById(R.id.textViewDetails);
        String status = "fdfdf";
        if (userDetailsExtras.getString("userDetailsDataIsSingle").equals("false")){
            status = "Not Single";
        } else if (userDetailsExtras.getString("userDetailsDataIsSingle").equals("true")){
            status = "Single";
        }

        textViewDetails.setText("Name : " + userDetailsExtras.getString("userDetailsDataName") + "\n" +
                "Gender : " + userDetailsExtras.getString("userDetailsDataGender") + "\n" +
                "Telephone Number : " + userDetailsExtras.getString("userDetailsDataTelNo") + "\n" +
                "Status : " + status + "\n" +
                "Blood Group : " + userDetailsExtras.getString("userDetailsDataBloodGroup") + "\n" +
                "Date of Birth : " + userDetailsExtras.getString("userDetailsDataDateOfBirth"));
    }
}
