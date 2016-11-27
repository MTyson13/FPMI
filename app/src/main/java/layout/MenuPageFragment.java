package layout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.martinbachvarov.fpmi.R;


public class MenuPageFragment extends Fragment implements View.OnClickListener {
    private TextView nameView;
    private static final String HI_MESSAGE = "Hi";
    private static final String ENGINEERING_PHYSICS = "Еngineering Physics";
    private static final String APPLIED_MATHEMATICS = "Applied Mathmematics";
    private static final String MATH_AND_INFORM = "Mathematics and Informatics";
    private static final String PHYSICS_INFO = "http://www.tu-sofia.bg/specialties/preview/641";
    private static final String APPLIED_MATH_INFO = "http://www.tu-sofia.bg/specialties/preview/638";
    private static final String APPLIED_MATH_INFORMATIC_INFO = "http://www.tu-sofia.bg/specialties/preview/737";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_page, container, false);
        nameView = (TextView) view.findViewById(R.id.nameView);

        Button webBtn = (Button) view.findViewById(R.id.specialityBtn);
        webBtn.setOnClickListener(this);

        Button facultyBtn = (Button) view.findViewById(R.id.facultyBtn);
        facultyBtn.setOnClickListener(this);

        Button programBtn = (Button) view.findViewById(R.id.programBtn);
        programBtn.setOnClickListener(this);

        Button addNotesButton = (Button) view.findViewById(R.id.addNoteBtn);
        addNotesButton.setOnClickListener(this);

        setNameView();
        return view;
    }

    private void setNameView() {
        String username = getArguments().getString("username");
        nameView.setText(HI_MESSAGE + ", " + username);
    }

    private void aboutFacultyInfo() {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.tu-sofia.bg/faculties/read/29"));
        startActivity(i);
    }

    //Set correct URL based on users' choice
    private void aboutSpecialityInfo() {
        String speciality = getArguments().getString("speciality");
        Intent brows = new Intent();
        if (speciality.equals(ENGINEERING_PHYSICS)) {
            brows = new Intent(Intent.ACTION_VIEW, Uri.parse(PHYSICS_INFO));
        } else if (speciality.equals(APPLIED_MATHEMATICS)) {
            brows = new Intent(Intent.ACTION_VIEW, Uri.parse(APPLIED_MATH_INFO));
        } else if (speciality.equals(MATH_AND_INFORM)) {
            brows = new Intent(Intent.ACTION_VIEW, Uri.parse(APPLIED_MATH_INFORMATIC_INFO));
        }
        startActivity(brows);
    }

    private void checkProgram() {
        ProgramFragment programPage = new ProgramFragment();

        //Save selected course
        Bundle value = new Bundle();
        value.putString("course", getArguments().getString("course"));
        programPage.setArguments(value);

        FragmentTransaction fTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fTransaction.replace(R.id.container, programPage).addToBackStack("menuTag");
        fTransaction.commit();
    }

    //On click Add Notes button open Notes fragment
    private void openNotes() {
        NotesFragment notesFragment = new NotesFragment();

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, notesFragment).addToBackStack("notesTag");
        fragmentTransaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //Set on click methods for all buttons
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.specialityBtn:
                aboutSpecialityInfo();
                break;

            case R.id.facultyBtn:
                aboutFacultyInfo();
                break;

            case R.id.programBtn:
                checkProgram();
                break;

            case R.id.addNoteBtn:
                openNotes();
                break;

            default:
                break;
        }
    }
}
