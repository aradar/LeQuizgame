package de.spitak.amazinggame.db.base;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by rschlett on 12/1/16.
 */

public interface Table {
    void onCreate(SQLiteDatabase db);

    void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion);

    void onDrop(SQLiteDatabase db);
}
