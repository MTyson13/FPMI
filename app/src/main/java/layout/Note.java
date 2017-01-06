package layout;

/**
 * Created by martin.bachvarov on 12/18/2016.
 */

public class Note {

    private String _noteText;
    private int _id;

    public Note() {

    }

    public Note(String noteText) {
        this._noteText = noteText;
    }

    public String getNoteText() {
        return _noteText;
    }

    public void setNoteText(String noteText) {
        this._noteText = noteText;
    }

    public int getNoteId() {
        return _id;
    }

    public void setNoteId(int id) {
        this._id = id;
    }
}
