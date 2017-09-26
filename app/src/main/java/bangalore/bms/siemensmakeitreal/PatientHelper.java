package bangalore.bms.siemensmakeitreal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.Serializable;

/**
 * Created by Harshit on 26-09-2017.
 */

public class PatientHelper extends SQLiteOpenHelper implements Serializable {
        public static final String DATABASE_NAME = "Patientdata_db";
        public static final int DATABASE_VERSION = 1;
        public static final String TABLE_PATIENT = "tbl_patientdata";
        public static final String CREATE_TABLE_PATIENT= "CREATE TABLE IF NOT EXISTS "+ TABLE_PATIENT + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NULL, age TEXT NULL,gender TEXT NULL,type TEXT NULL,score_1 INTEGER NULL,score_2 INTEGER NULL,f_1 FLOAT NULL,,f_2 FLOAT NULL,f_3 FLOAT NULL,f_4 FLOAT NULL,f_5 FLOAT NULL,t_1 LONG NULL,t_2 LONG NULL,t_3 LONG NULL,t_4 LONG NULL,t_5 LONG NULL,t_6 LONG NULL,t_7 LONG NULL,t_8 LONG NULL,t_9 LONG NULL,t_10 LONG NULL);";
        public static final String DELETE_TABLE_PATIENT ="DROP TABLE IF EXISTS " + TABLE_PATIENT;
        public PatientHelper(Context context) {
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
        public void insertData(String _name,String _age,String _gender,String _type) {

            // Open the database for writing
            SQLiteDatabase db = this.getWritableDatabase();
            // Start the transaction.
            db.beginTransaction();
            ContentValues values;

            try {
                values = new ContentValues();
                values.put("name", _name);
                values.put("age", _age);
                values.put("gender", _gender);
                values.put("type", _type);

                // Insert Row
                long i = db.insert(TABLE_PATIENT, null, values);
                Log.i("Insert", i + "");
                // Insert into database successfully.
                db.setTransactionSuccessful();

            } catch (SQLiteException e) {
                e.printStackTrace();

            } finally {
                db.endTransaction();
                // End the transaction.
                db.close();
                // Close database
            }
        }
            public void insertData(int score1){
                // Open the database for writing
                SQLiteDatabase db = this.getWritableDatabase();
                // Start the transaction.
                db.beginTransaction();
                ContentValues values;

                try {
                    values = new ContentValues();
                    values.put("score1", score1);


                    // Insert Row
                    long i = db.insert(TABLE_PATIENT, null, values);
                    Log.i("Insert", i + "");
                    // Insert into database successfully.
                    db.setTransactionSuccessful();

                } catch (SQLiteException e) {
                    e.printStackTrace();

                } finally {
                    db.endTransaction();
                    // End the transaction.
                    db.close();
                    // Close database
                }

            }
    public void insertData(int score2,int flag){
        // Open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Start the transaction.
        db.beginTransaction();
        ContentValues values;

        try {
            values = new ContentValues();
            values.put("score1", score2);


            // Insert Row
            long i = db.insert(TABLE_PATIENT, null, values);
            Log.i("Insert", i + "");
            // Insert into database successfully.
            db.setTransactionSuccessful();

        } catch (SQLiteException e) {
            e.printStackTrace();

        } finally {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }

    }
    public void insertData(float f_1,float f_2,float f_3,float f_4,float f_5){
        // Open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Start the transaction.
        db.beginTransaction();
        ContentValues values;

        try {
            values = new ContentValues();
            values.put("f_1", f_1);
            values.put("f_2", f_2);
            values.put("f_3", f_3);
            values.put("f_4", f_4);
            values.put("f_5", f_5);


            // Insert Row
            long i = db.insert(TABLE_PATIENT, null, values);
            Log.i("Insert", i + "");
            // Insert into database successfully.
            db.setTransactionSuccessful();

        } catch (SQLiteException e) {
            e.printStackTrace();

        } finally {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }

    }
    public void insertData(long t_1,long t_2,long t_3,long t_4,long t_5,long t_6,long t_7,long t_8,long t_9,long t_10){
        // Open the database for writing
        SQLiteDatabase db = this.getWritableDatabase();
        // Start the transaction.
        db.beginTransaction();
        ContentValues values;

        try {
            values = new ContentValues();
            values.put("t_1", t_1);
            values.put("t_2", t_2);
            values.put("t_3", t_3);
            values.put("t_4", t_4);
            values.put("t_5", t_5);
            values.put("t_6", t_6);
            values.put("t_7", t_7);
            values.put("t_8", t_8);
            values.put("t_9", t_9);
            values.put("t_10",t_10);


            // Insert Row
            long i = db.insert(TABLE_PATIENT, null, values);
            Log.i("Insert", i + "");
            // Insert into database successfully.
            db.setTransactionSuccessful();

        } catch (SQLiteException e) {
            e.printStackTrace();

        } finally {
            db.endTransaction();
            // End the transaction.
            db.close();
            // Close database
        }

    }
    public Cursor getData(String timestamp, String vendorname){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM"+TABLE_PATIENT , null);
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
        return c;
    }



}


