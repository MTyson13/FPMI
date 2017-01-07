package layout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by martin.bachvarov on 11/18/2016.
 */

public class DBAdapter extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "notes.db";
    private static final String NOTES_TABLE = "newnotesNew";
    private static final String COLUMN_ROWID = "_id";
    private static final String COLUMN_NOTE = "note";
    private static final String CREATE_DB_QUERY = "CREATE TABLE " + NOTES_TABLE + "(" + COLUMN_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT " + COLUMN_NOTE + " TEXT " + ");";
    private static final String GET_NOTE_QUERY = "SELECT * FROM " + NOTES_TABLE + " ORDER BY " + COLUMN_ROWID + " ASC LIMIT 1;";
    private static final String IS_RECORD_EXIST = "SELECT * FROM " + NOTES_TABLE + " ORDER BY " + COLUMN_ROWID;

    private final Context context;
    private static SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = ctx;
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_DB_QUERY);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(Constants.DB_LOG_TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + NOTES_TABLE);
        onCreate(db);
    }

    //---insert a note into the database---
    public long insertNote(Note note) {
        ContentValues vlaues = new ContentValues();
        vlaues.put(COLUMN_NOTE, note.getNoteText());

        long result = db.insert(NOTES_TABLE, null, vlaues);

        Log.d(Constants.DB_LOG_TAG, "status:" + result + " noteText:" + note.getNoteText());
        return result;
    }

    //---deletes a particular note---
    public boolean deleteNote(long rowId) {
        return db.delete(NOTES_TABLE, COLUMN_ROWID + "=" + rowId, null) > 0;
    }

    //---retrieves a particular note---
    public String getNote() throws SQLException {
        String noteString;
        Cursor cursor = db.rawQuery(GET_NOTE_QUERY, null);
        cursor.moveToFirst();

        noteString = cursor.getString(cursor.getColumnIndex("note"));

        return noteString;
    }

    //---updates a note---
    public boolean updateNote(Note note) {
        ContentValues args = new ContentValues();
        args.put(COLUMN_NOTE, note.getNoteText());
        return db.update(NOTES_TABLE, args, COLUMN_ROWID + "= 1", null) > 0;
    }

    public void addNoteToTheDB(Note note) {
        Cursor c = db.rawQuery(IS_RECORD_EXIST, null);
        if (c.getCount() <= 0) {
            insertNote(note);
            c.close();
        } else {
            updateNote(note);
            c.close();
        }
        Toast.makeText(context, "Successfully saved", Toast.LENGTH_SHORT).show();
    }

    //---closes the database---
    public static void closeDB() {
        if (db != null) {
            db.close();
            db = null;
        }
    }
}
