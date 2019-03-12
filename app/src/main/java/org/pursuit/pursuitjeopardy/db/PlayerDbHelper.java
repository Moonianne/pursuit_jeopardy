package org.pursuit.pursuitjeopardy.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PlayerDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "cat.db";
    public static final String TABLE_NAME = "cats";
    public static final int SCHEMA_VERSION = 1;

    public PlayerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "last_name TEXT, name TEXT, fur_color TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addPlayer(Player player) {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE name = '" + player.getName() +
                "' AND score = '" + player.getScore() + "';", null);
        if (cursor.getCount() == 0) {
            getWritableDatabase().execSQL("INSERT INTO " + TABLE_NAME +
                    "(name, score) VALUES('" +
                    player.getName() + "', '" +
                    player.getScore() + "');");
        }
        cursor.close();
    }
}
