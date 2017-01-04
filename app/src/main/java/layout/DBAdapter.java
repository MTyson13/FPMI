package layout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by martin.bachvarov on 11/18/2016.
 */

public class DBAdapter extends SQLiteOpenHelper {

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NOTE = "note";
    private static final String TAG = "DBAdapter";
    private static final String DATABASE_NAME = "MyDB";
    private static final String DATABASE_TABLE = "notes";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
            "create table if not exists notes(_id integer primary key autoincrement, "
                    + "note text not null);";

    private static final String GET_NOTE_QUERY = "SELECT * FROM NOTE";
    private final Context context;
    private static SQLiteDatabase db;

    public DBAdapter(Context ctx) {
        super(ctx, DATABASE_CREATE, null, DATABASE_VERSION);
        this.context = ctx;
        db = this.getWritableDatabase();
    }

    //---closes the database---
    public static void closeDB() {
        if (db != null) {
            db.close();
            db = null;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(DATABASE_CREATE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(Constants.DB_LOG_TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(db);
    }


    //---insert a contact into the database---
    public long insertNote(Note note) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NOTE, note.getNoteText());

        long result = db.insert(DATABASE_TABLE, null, initialValues);

        Log.d(Constants.DB_LOG_TAG, "status:" + result + " noteTExt:" + note.getNoteText());
        return result;
    }


    //---deletes a particular contact---
    public boolean deleteNote(long rowId) {
        return db.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }


    //---retrieves all the contacts---
    public Cursor getAllNotes() {
        return db.query(DATABASE_TABLE, new String[]{KEY_ROWID, KEY_NOTE},
                null, null, null, null, null);

    }


    //---retrieves a particular contact---
    public Note getNote(String userName) throws SQLException {
        Cursor cursor = db.rawQuery(GET_NOTE_QUERY + "Martin", null);
        Note note = null;
        if (cursor.getCount() > 0) {
            note = new Note();
            note.setNoteText(cursor.getString(1));
        }

        return note;
    }


    //---updates a contact---
    public boolean updateNote(long rowId, String name, String email) {
        ContentValues args = new ContentValues();
        args.put(KEY_NOTE, name);
        return db.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
