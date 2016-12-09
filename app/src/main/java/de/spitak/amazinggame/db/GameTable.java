package de.spitak.amazinggame.db;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by rschlett on 12/1/16.
 */

public class GameTable implements Table {

    private static final String createGameTable = "CREATE TABLE " + TableColumns.TABLE_NAME + " (" +
            TableColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TableColumns.NAME + " VARCHAR(255), " +
            TableColumns.DESCRIPTION + " VARCHAR(255), " +
            TableColumns.IMAGE + " VARCHAR(255));";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createGameTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TableColumns.TABLE_NAME + ";");
        onCreate(db);
    }

    public static class TableColumns implements de.spitak.amazinggame.db.TableColumns {
        public static final String TABLE_NAME = "game";

        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String IMAGE = "image";

        public static final String[] COLUMN_NAME = new String[]{_ID, NAME, DESCRIPTION, IMAGE};
    }
}
