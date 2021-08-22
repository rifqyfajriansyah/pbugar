package inside.books.trypns.databaseHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import inside.books.trypns.data.DataUser;

public class Core extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bugar.db";
    private static final int DATABASE_VERSION = 1;

    public Core(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DataUser.CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + DataUser.TABLE_NAME);
        onCreate(db);
    }

}
