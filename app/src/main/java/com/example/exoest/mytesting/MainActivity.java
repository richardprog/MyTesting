package com.example.exoest.mytesting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.text.SimpleDateFormat;

import layout.DetailsFormFragment;
import layout.SubmitButtonFragment;

public class MainActivity extends AppCompatActivity implements SubmitButtonFragment.SubmitButtonListener, DetailsFormFragment.DetailsFormListener {

    DetailsFormFragment detailsFormFragment;
    SubmitButtonFragment submitButtonFragment;
    private final int REQUEST_CODE_FOR_SECOND_INTENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);

        detailsFormFragment = (DetailsFormFragment) getFragmentManager().findFragmentById(R.id.fragmentDetailsInfo);
        submitButtonFragment = (SubmitButtonFragment) getFragmentManager().findFragmentById(R.id.fragmentSubmitButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent i = new Intent();

        switch (id) {
            case R.id.menuItemAnimalDog:
                i.setClass(this, DogActivity.class);
                startActivityForResult(i, REQUEST_CODE_FOR_SECOND_INTENT);
                return true;
            case R.id.menuItemAnimalCat:
                i.setClass(this, CatActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_FOR_SECOND_INTENT) {
            if (resultCode == RESULT_OK) {
                // call for setting the returned string
                detailsFormFragment.setReturnedText(data.getStringExtra("fromDog"));
            }
        }
    }

    // Implemented function from SubmitButtonFragment
    @Override
    public void retrieveDetailsInfo() {
        UserDetails userDetails = detailsFormFragment.getUserDetailsInfo();
        Intent i = new Intent(this, RetrievedDetailsActivity.class);
        i.putExtra("userDetailsDataName", userDetails.getName());
        i.putExtra("userDetailsDataGender", userDetails.getGender());
        i.putExtra("userDetailsDataTelNo", userDetails.getTelNo());
        i.putExtra("userDetailsDataIsSingle", String.valueOf(userDetails.isSingle()));
        i.putExtra("userDetailsDataBloodGroup", userDetails.getBloodGroup());
        i.putExtra("userDetailsDataDateOfBirth", new SimpleDateFormat("yyyy/mm/dd").format(userDetails.getDateOfBirth()));
        startActivity(i);
    }

    // Implemented function from DetailsFormFragment
    @Override
    public void setButtonSubmitInfoEnabled(String greetingMessage) {
        submitButtonFragment.setButtonSubmitInfoEnabled(greetingMessage);
    }
}
