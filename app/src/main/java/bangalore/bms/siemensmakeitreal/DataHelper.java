package bangalore.bms.siemensmakeitreal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Harshit on 24-09-2017.
 */

public class DataHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Patient_db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_PATIENT = "tbl_patient";
    public static final String CREATE_TABLE_PATIENT= "CREATE TABLE IF NOT EXISTS "+ TABLE_PATIENT + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NULL, age TEXT NULL,gender TEXT NULL,type TEXT NULL)";
    public static final String DELETE_TABLE_PATIENT ="DROP TABLE IF EXISTS " + TABLE_PATIENT;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_PATIENT);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DELETE_TABLE_PATIENT);
        //Create tables again
        onCreate(db);
    }
    public void insertData(String _name,String _age,String _gender,String _type){

        // Open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Start the transaction.
        db.beginTransaction();
        ContentValues values;

        try
        {
            values = new ContentValues();
            values.put("name",_name);
            values.put("age",_age);
            values.put("gender",_gender);
            values.put("type",_type);

            // Insert Row
            long i = db.insert(TABLE_PATIENT, null, values);
            Log.i("Insert", i + "");
            // Insert into database successfully.
            db.setTransactionSuccessful();

        }
        catch (SQLiteException e)
        {
            e.printStackTrace();

        }
        finally
        {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }

    }
}
