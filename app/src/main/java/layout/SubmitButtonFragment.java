package layout;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.exoest.mytesting.R;

public class SubmitButtonFragment extends Fragment {

    SubmitButtonListener activityCommander;
    Button buttonSubmitInfo;

    public SubmitButtonFragment() {
    }

    // function that provided for MainActivity to implement
    public interface SubmitButtonListener{
        public void retrieveDetailsInfo();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            activityCommander = (SubmitButtonListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_submit_button, container, false);

        buttonSubmitInfo = (Button) view.findViewById(R.id.buttonSubmitInfo);
        buttonSubmitInfo.setEnabled(false);
        buttonSubmitInfo.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        onClickButtonSubmitInfo(v);
                    }
                }
        );
        return view;
    }

    public void onClickButtonSubmitInfo(View view){
        activityCommander.retrieveDetailsInfo();
    }

    // called by MainActivity, but actually requested by DetailsFormFragment after radio button event occurs
    public void setButtonSubmitInfoEnabled(String greetingMessage){
        buttonSubmitInfo.setText(greetingMessage);
        buttonSubmitInfo.setEnabled(true);
    }
}
