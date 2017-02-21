package layout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.martinbachvarov.fpmi.R;


public class MenuPageFragment extends Fragment implements View.OnClickListener {
    private TextView nameView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_page, container, false);
        nameView = (TextView) view.findViewById(R.id.nameView);

        Toolbar myToolbar = (Toolbar) view.findViewById(R.id.myToolbar);
        myToolbar.setTitle("F P M I");
        myToolbar.inflateMenu(R.menu.menu_main);
        myToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.toolbar_notes:
                        openNotes();
                        return true;
                    case R.id.toolbar_faculty:
                        aboutFacultyInfo();
                        return true;
                    case R.id.toolbar_speciality:
                        aboutSpecialityInfo();
                        return true;
                    case R.id.toolbar_programs:
                        checkProgram();
                        return true;
                    default:
                        return MenuPageFragment.super.onOptionsItemSelected(item);
                }
            }
        });
        myToolbar.setNavigationIcon(getResources().getDrawable(R.drawable.common_google_signin_btn_icon_light_normal));
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMainMenu();
            }
        });

        Button webBtn = (Button) view.findViewById(R.id.specialityBtn);
        webBtn.setOnClickListener(this);

        Button facultyBtn = (Button) view.findViewById(R.id.facultyBtn);
        facultyBtn.setOnClickListener(this);

        Button programBtn = (Button) view.findViewById(R.id.programBtn);
        programBtn.setOnClickListener(this);

        Button addNotesButton = (Button) view.findViewById(R.id.addNoteBtn);
        addNotesButton.setOnClickListener(this);

        Button cameraBtn = (Button) view.findViewById(R.id.cameraBtn);
        cameraBtn.setOnClickListener(this);

        setNameView();
        return view;
    }

    private void setNameView() {
        String username = getArguments().getString("username");
        nameView.setText(Constants.HI_MESSAGE + ", " + username);
    }

    private void aboutFacultyInfo() {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.ABOUT_FACULTY_INFO));
        startActivity(i);
    }

    //Set correct URL based on users' choice
    private void aboutSpecialityInfo() {
        String speciality = getArguments().getString("speciality");
        Intent brows = new Intent();
        if (speciality.equals(Constants.ENGINEERING_PHYSICS)) {
            brows = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.PHYSICS_INFO));
        } else if (speciality.equals(Constants.APPLIED_MATHEMATICS)) {
            brows = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.APPLIED_MATH_INFO));
        } else if (speciality.equals(Constants.MATH_AND_INFORM)) {
            brows = new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.APPLIED_MATH_INFORMATIC_INFO));
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
        fTransaction.replace(R.id.container, programPage).addToBackStack("menuFragment");
        fTransaction.commit();
    }

    //On click Add Notes button open Notes fragment
    private void openNotes() {
        NotesFragment notesFragment = new NotesFragment();

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, notesFragment).addToBackStack("notesFragment");
        fragmentTransaction.commit();
    }

    private void backToMainMenu() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();
    }

    private void openCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);
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

            case R.id.cameraBtn:
                openCamera();
                break;

            default:
                break;
        }
    }
}
