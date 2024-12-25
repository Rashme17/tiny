package com.example.tiny;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "Tiny.db";
    private static final int DATABASE_VERSION = 3;  // Increment version to handle schema change

    // New Users table (User1)
    private static final String TABLE_USERS1 = "User1";
    private static final String COLUMN_USER1_ID = "UserID";
    private static final String COLUMN_USERNAME1 = "Username";
    private static final String COLUMN_PASSWORD1 = "Password";

    // New Child table (Child1)
    private static final String TABLE_CHILD1 = "Child1";
    private static final String COLUMN_CHILD1_ID = "ChildID";
    private static final String COLUMN_USER1_ID_FK = "UserID";
    private static final String COLUMN_CHILD1_NAME = "ChildName";
    private static final String COLUMN_AGE1 = "Age";
    private static final String COLUMN_WEIGHT1 = "Weight";
    private static final String COLUMN_HEIGHT1 = "Height";
    private static final String COLUMN_BMI1 = "BMI";
    private static final String COLUMN_NUTRITION_STATUS1 = "NutritionStatus";  // New column for nutrition status

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create new User1 table
        String createUsersTable = "CREATE TABLE " + TABLE_USERS1 + "(" +
                COLUMN_USER1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME1 + " TEXT UNIQUE, " +
                COLUMN_PASSWORD1 + " TEXT)";

        // Create new Child1 table
        String createChildTable = "CREATE TABLE " + TABLE_CHILD1 + "(" +
                COLUMN_CHILD1_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER1_ID_FK + " INTEGER, " +
                COLUMN_CHILD1_NAME + " TEXT, " +
                COLUMN_AGE1 + " INTEGER, " +
                COLUMN_WEIGHT1 + " REAL, " +
                COLUMN_HEIGHT1 + " REAL, " +
                COLUMN_BMI1 + " REAL, " +
                COLUMN_NUTRITION_STATUS1 + " TEXT, " +  // Add NutritionStatus column
                "FOREIGN KEY(" + COLUMN_USER1_ID_FK + ") REFERENCES " + TABLE_USERS1 + "(" + COLUMN_USER1_ID + "))";

        db.execSQL(createUsersTable);
        db.execSQL(createChildTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // When upgrading the database, simply drop the old tables and create new ones
        if (oldVersion < 3) {
            // Drop old tables (in case they exist from previous versions)
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS1);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHILD1);
            // Recreate tables with new schema
            onCreate(db);
        }
    }

    public boolean addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME1, username);
        values.put(COLUMN_PASSWORD1, password);
        long result = db.insert(TABLE_USERS1, null, values);
        return result != -1;
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS1, null,
                COLUMN_USERNAME1 + "=? AND " + COLUMN_PASSWORD1 + "=?",
                new String[]{username, password},
                null, null, null);
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }


    public int getUserId(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        int userId = -1;

        try {
            String[] columns = {COLUMN_USER1_ID};
            String selection = COLUMN_USERNAME1 + "=? AND " + COLUMN_PASSWORD1 + "=?";
            String[] selectionArgs = {username, password};

            Cursor cursor = db.query(
                    TABLE_USERS1,
                    columns,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int columnIndex = cursor.getColumnIndexOrThrow(COLUMN_USER1_ID);
                    if (columnIndex != -1) {
                        userId = cursor.getInt(columnIndex);
                    }
                }
                cursor.close();
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting user ID: ", e);
        }

        return userId;
    }

    public boolean addChild(int userId, String name, int age, float weight, float height) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        try {
            float heightInMeters = height / 100;
            float bmi = weight / (heightInMeters * heightInMeters);

            String nutritionStatus = calculateNutritionStatus(bmi);

            values.put(COLUMN_USER1_ID_FK, userId);
            values.put(COLUMN_CHILD1_NAME, name);
            values.put(COLUMN_AGE1, age);
            values.put(COLUMN_WEIGHT1, weight);
            values.put(COLUMN_HEIGHT1, height);
            values.put(COLUMN_BMI1, bmi);
            values.put(COLUMN_NUTRITION_STATUS1, nutritionStatus);

            long result = db.insert(TABLE_CHILD1, null, values);
            return result != -1;
        } catch (Exception e) {
            Log.e(TAG, "Error adding child: ", e);
            return false;
        }
    }

    public String calculateNutritionStatus(float bmi) {
        if (bmi < 16) {
            return "Severely Underweight";
        } else if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    public Cursor getChildByUserId(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                COLUMN_CHILD1_ID,
                COLUMN_CHILD1_NAME,
                COLUMN_AGE1,
                COLUMN_WEIGHT1,
                COLUMN_HEIGHT1,
                COLUMN_BMI1,
                COLUMN_NUTRITION_STATUS1
        };
        String selection = COLUMN_USER1_ID_FK + "=?";
        String[] selectionArgs = {String.valueOf(userId)};

        Cursor cursor = db.query(TABLE_CHILD1, columns, selection, selectionArgs, null, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                for (String column : columns) {
                    try {
                        int columnIndex = cursor.getColumnIndexOrThrow(column);
                        Log.d(TAG, "Column " + column + " Index: " + columnIndex);
                    } catch (IllegalArgumentException e) {
                        Log.e(TAG, "Column " + column + " not found.", e);
                    }
                }
            } else {
                cursor.close();
                Log.w(TAG, "No child data found for user: " + userId);
                return null;
            }
        }

        return cursor;
    }
}
