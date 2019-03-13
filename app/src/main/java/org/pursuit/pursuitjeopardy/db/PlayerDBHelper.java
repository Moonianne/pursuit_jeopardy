package org.pursuit.pursuitjeopardy.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.pursuit.pursuitjeopardy.model.Player;

public final class PlayerDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "player.db";
    private static final String TABLE_NAME = "player_table";
    private static final int SCHEMA_VERSION = 3;

    public PlayerDBHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, score INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addPlayer(Player player) {
        Cursor cursor = getReadableDatabase().rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE name = '" + player.getName() +
                        "' AND score = '" + player.getCurrentScore() + "';", null);
        if (cursor.getCount() == 0) {
            getWritableDatabase().execSQL("INSERT INTO " + TABLE_NAME +
                    "(name, score) VALUES('" +
                    player.getName() + "','" +
                    player.getCurrentScore() + "');");
        }
        cursor.close();
    }
}