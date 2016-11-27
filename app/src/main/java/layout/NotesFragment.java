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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notes, container, false);
        noteText = (EditText) view.findViewById(R.id.noteText);

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
}
