package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.martinbachvarov.fpmi.R;

public class HomeLoginFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    String[] degree = {"Bachelor", "Magister"};
    String[] bachSpeciality = {"Еngineering Physics", "Applied Mathematics", "Mathematics and Informatics"};
    String[] magisterSpeciality = {"Applied Mathematics"};
    String[] bachCourse = {"I", "II", "III", "IV"};
    String[] magisterCourse = {"First"};
    private EditText nameInput;
    private Spinner degreeSpnr, specialitySpnr, courseSpnr;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_login_fragment, container, false);

        nameInput = (EditText) view.findViewById(R.id.homeNameInput);

        Button btnContinue = (Button) view.findViewById(R.id.buttonHomeContinue);

        degreeSpnr = (Spinner) view.findViewById(R.id.selectDegreeSpinner);
        degreeSpnr.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayAdapter<String> degreeAdapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, degree);
        degreeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        degreeSpnr.setAdapter(degreeAdapter);

        specialitySpnr = (Spinner) view.findViewById(R.id.specialitySpinner);
        specialitySpnr.setEnabled(false);

        courseSpnr = (Spinner) view.findViewById(R.id.selectCourseSpinner);
        courseSpnr.setEnabled(false);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFragment();
            }
        });

        return view;
    }

    //Check is name input null
    private boolean checkNameInputIsNull() {
        if (nameInput.getText().toString().trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    private void openFragment() {
        if (checkNameInputIsNull()) {
            nameInput.setError("First name is required");
        } else {
            //Set username, speciality and course values selected in the spinner by the user
            MenuPageFragment fragment = new MenuPageFragment();

            Bundle value = new Bundle();
            value.putString("username", nameInput.getText().toString());
            value.putString("speciality", specialitySpnr.getSelectedItem().toString());
            value.putString("course", courseSpnr.getSelectedItem().toString());
            fragment.setArguments(value);

            //Replace HomeLoginFragment with MenuPageFragment
            FragmentTransaction fTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fTransaction.replace(R.id.container, fragment).addToBackStack("loginTag");
            fTransaction.commit();
        }
    }

    //Fill data into Spinners depends on user choice- Bachelor or Magister
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        specialitySpnr.setEnabled(true);
        courseSpnr.setEnabled(true);
        if (degreeSpnr.getSelectedItem().equals("Bachelor")) {
            ArrayAdapter<String> specialityData = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, bachSpeciality);
            specialityData.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            specialitySpnr.setAdapter(specialityData);

            ArrayAdapter<String> courseData = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, bachCourse);
            courseData.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            courseSpnr.setAdapter(courseData);
        } else if (degreeSpnr.getSelectedItem().equals("Magister")) {
            ArrayAdapter<String> specialityData = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, magisterSpeciality);
            specialityData.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            specialitySpnr.setAdapter(specialityData);

            ArrayAdapter<String> courseData = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, magisterCourse);
            courseData.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            courseSpnr.setAdapter(courseData);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
