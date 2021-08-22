package inside.books.trypns.databaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import inside.books.trypns.data.DataUser;

public class DbUser {

    private Context context;
    private SQLiteDatabase database;
    private Core dbHelper;

    public DbUser(Context context){
        this.context = context;
        dbHelper = new Core(context);
    }

    private void open() throws SQLiteException {

        database = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }

    public void insertUser (DataUser dataUser) {
        open();

        ContentValues values = new ContentValues();
        values.put(DataUser.COLUMN_NAMA, dataUser.getNama());
        values.put(DataUser.COLUMN_EMAIL, dataUser.getEmail());
        values.put(DataUser.COLUMN_PASSWORD, dataUser.getPassword());

        database.insert(DataUser.TABLE_NAME, null, values);

        database.close();
        close();

    }

    public DataUser getUser(String email, String password) {

        open();
        DataUser dataUser = null;

        String where = DataUser.COLUMN_EMAIL+" = ? AND "+DataUser.COLUMN_PASSWORD+" = ? ";
        Cursor cursor = database.query(DataUser.TABLE_NAME,
                new String[]{DataUser.COLUMN_NAMA, DataUser.COLUMN_EMAIL, DataUser.COLUMN_PASSWORD},
                where,
                new String[]{email, password}, null, null, null, null);


        if (cursor.moveToFirst()) {

            dataUser = new DataUser(
                    cursor.getString(cursor.getColumnIndex(DataUser.COLUMN_NAMA)),
                    cursor.getString(cursor.getColumnIndex(DataUser.COLUMN_EMAIL)),
                    cursor.getString(cursor.getColumnIndex(DataUser.COLUMN_PASSWORD)));

            // close the db connection

        }
        cursor.close();

        database.close();
        close();

        return dataUser;
    }

    private DataUser cekUser(String nama, String email) {

        open();
        DataUser dataUser = null;

        String where = DataUser.COLUMN_NAMA+" = ? AND "+DataUser.COLUMN_EMAIL+" = ? ";
        Cursor cursor = database.query(DataUser.TABLE_NAME,
                new String[]{DataUser.COLUMN_NAMA, DataUser.COLUMN_EMAIL, DataUser.COLUMN_PASSWORD},
                where,
                new String[]{nama, email}, null, null, null, null);


        if (cursor.moveToFirst()) {

            dataUser = new DataUser(
                    cursor.getString(cursor.getColumnIndex(DataUser.COLUMN_NAMA)),
                    cursor.getString(cursor.getColumnIndex(DataUser.COLUMN_EMAIL)),
                    cursor.getString(cursor.getColumnIndex(DataUser.COLUMN_PASSWORD)));

            // close the db connection

        }
        cursor.close();

        database.close();
        close();

        return dataUser;
    }


    public int setInsert(DataUser dataUser){

        DataUser dataCek = cekUser(dataUser.getNama(), dataUser.getEmail());
        if(dataCek==null){
            insertUser(dataUser);
            return 1;
        }else{
            return 0;
        }

    }

}
