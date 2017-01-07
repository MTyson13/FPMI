package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.martinbachvarov.fpmi.R;

public class NotesFragment extends Fragment implements View.OnClickListener {

    private EditText noteText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        noteText = (EditText) view.findViewById(R.id.noteText);

        getNoteFromDBAndPopulate();

        Button saveNoteBtn = (Button) view.findViewById(R.id.saveNoteBtn);
        saveNoteBtn.setOnClickListener(this);
        Button cancelNoteBtn = (Button) view.findViewById(R.id.cancelNoteBtn);
        cancelNoteBtn.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveNoteBtn:
                //save note into DB
                Note note = new Note();
                note.setNoteText(noteText.getText().toString());
                DBAdapter dbAdapter = new DBAdapter(getContext());
                dbAdapter.addNoteToTheDB(note);
                backToPreviousFragment();
                break;

            case R.id.cancelNoteBtn:
                backToPreviousFragment();
                break;

            default:
                break;
        }
    }

    private long saveNoteToDB(Note note) {
        DBAdapter dbAdapter = new DBAdapter(getContext());
        long saveResult = dbAdapter.insertNote(note);
        Toast.makeText(getContext(), "Successfully saved", Toast.LENGTH_SHORT).show();
        return saveResult;
    }

    //Back to the previous fragment on click
    private void backToPreviousFragment() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.popBackStack();
    }

    private void getNoteFromDBAndPopulate() {
        DBAdapter dbAdapter = new DBAdapter(getContext());
        noteText.setText(dbAdapter.getNote());
    }
}
