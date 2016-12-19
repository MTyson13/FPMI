package layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.martinbachvarov.fpmi.R;

public class NotesFragment extends Fragment implements View.OnClickListener {

    private EditText noteText;
    private EditText userText;

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
                //TODO: check if is successfully saved///
                saveNoteToDB(note);
                break;

            case R.id.cancelNoteBtn:
                FragmentTransaction fTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fTransaction.replace(R.id.container, new MenuPageFragment());
                fTransaction.commit();
                break;

            default:
                break;
        }
    }

    private long saveNoteToDB(Note note) {
        DBAdapter dbAdapter = new DBAdapter(getContext());
        long saveResult = dbAdapter.insertNote(note);

        return saveResult;
    }

    private void getNoteFromDBAndPopulate() {
        DBAdapter dbAdapter = new DBAdapter(getContext());
        Note note = dbAdapter.getNote("Martin");

        String userName = note.getUser();
        String noteText = note.getNoteText();

        this.noteText.setText(noteText);
    }
}
