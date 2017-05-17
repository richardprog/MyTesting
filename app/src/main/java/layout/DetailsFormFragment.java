package layout;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.exoest.mytesting.R;
import com.example.exoest.mytesting.UserDetails;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DetailsFormFragment extends Fragment {

    DetailsFormListener     activityCommander;
    EditText                editTextName;
    RadioGroup              radioGroupGender;
    RadioButton             radioButtonGender;
    EditText                editTextTelNo;
    TextView                textViewReturnedText;
    ToggleButton            toggleButtonIsSingle;
    Spinner                 spinnerBloodGroup;
    EditText                editTextDateOfBirth;
    View                    view;

    public DetailsFormFragment() {
    }

    // function that provided for MainActivity to implement
    public interface DetailsFormListener{
        public void setButtonSubmitInfoEnabled(String greetingMessage);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            activityCommander = (DetailsFormListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_details_form, container, false);

        editTextName            = (EditText)        view.findViewById(R.id.editTextName);
        radioGroupGender        = (RadioGroup)      view.findViewById(R.id.radioGroupGender);
        editTextTelNo           = (EditText)        view.findViewById(R.id.editTextTelNo);
        textViewReturnedText    = (TextView)        view.findViewById(R.id.textViewReturnedText);
        toggleButtonIsSingle    = (ToggleButton)    view.findViewById(R.id.toggleButtonIsSingle);
        spinnerBloodGroup       = (Spinner)         view.findViewById(R.id.spinnerBloodGroup);
        editTextDateOfBirth     = (EditText)        view.findViewById(R.id.editTextDateOfBirth);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), R.array.blood_group, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBloodGroup.setAdapter(adapter);

        radioGroupGender.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener(){
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        onCheckedChangedGender(checkedId);
                    }
                }
        );

        editTextDateOfBirth.setOnTouchListener(
                new EditText.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_UP){
                            DialogFragment newFragment = new DatePickerFragment();
                            newFragment.show(getFragmentManager(), "DatePicker");
                        }
                        return true;
                    }
                }
        );
        return view;
    }

    // called by MainActivity for validation
    public boolean isAllEditTextFilled(){
        if (!(editTextName.getText().toString().equals("")) &&
                !(editTextTelNo.getText().toString().equals("")) &&
                !(editTextDateOfBirth.getText().toString().equals(""))){
            return true;
        } else {
            return false;
        }
    }

    // called by MainActivity, but actually requested by SubmitButtonFragment
    public UserDetails getUserDetailsInfo(){
        String name = "null";
        String gender = "null";
        String telNo = "null";
        boolean isSingle = false;
        String bloodGroup = "null";
        Date dateOfBirth = new Date();
        try {
            radioButtonGender = (RadioButton) view.findViewById(radioGroupGender.getCheckedRadioButtonId());

            name = editTextName.getText().toString();
            gender = radioButtonGender.getText().toString();
            telNo = editTextTelNo.getText().toString();
            isSingle = toggleButtonIsSingle.isChecked();
            bloodGroup = spinnerBloodGroup.getSelectedItem().toString();
            dateOfBirth = new SimpleDateFormat("yyyy/mm/dd").parse(editTextDateOfBirth.getText().toString());
        } catch (Exception ex) {
            Toast.makeText(view.getContext(), ex.toString(), Toast.LENGTH_LONG).show();
        }
        return new UserDetails(name, gender, telNo, isSingle, bloodGroup, dateOfBirth);

    }

    // called by MainActivity
    public void setReturnedText(String returnedText){
        textViewReturnedText.setText(returnedText);
    }

    public void onCheckedChangedGender(int checkedId){
        String greetingMessage = getString(R.string.activity_main_button_gender_default);

        RadioButton rbMale = (RadioButton) view.findViewById(R.id.radioButtonMale);
        RadioButton rbFemale = (RadioButton) view.findViewById(R.id.radioButtonFemale);
        int idMale = rbMale.getId();
        int idFemale = rbFemale.getId();

        if (checkedId == idMale){
            greetingMessage = getString(R.string.activity_main_button_gender_change_male);
        } else if (checkedId == idFemale){
            greetingMessage = getString(R.string.activity_main_button_gender_change_female);
        }

        activityCommander.setButtonSubmitInfoEnabled(greetingMessage);
    }

    @SuppressLint("ValidFragment")
    public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            populateSetDate(year, month + 1, dayOfMonth);
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar calendar = Calendar.getInstance();
            int yy = calendar.get(Calendar.YEAR);
            int mm = calendar.get(Calendar.MONTH);
            int dd = calendar.get(Calendar.DAY_OF_MONTH);
            return new DatePickerDialog(getActivity(), this, yy, mm, dd);
        }
    }

    public void populateSetDate(int year, int month, int day){
        editTextDateOfBirth.setText(year + "/" + month + "/" + day);
    }
}
