package de.spitak.amazinggame.db.table;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import de.spitak.amazinggame.db.base.Table;

import static de.spitak.amazinggame.db.table.PlayerTable.PlayerTableColumns.NAME;
import static de.spitak.amazinggame.db.table.PlayerTable.PlayerTableColumns.TABLE_NAME;
import static de.spitak.amazinggame.db.table.PlayerTable.PlayerTableColumns._ID;

/**
 * Created by rschlett on 12/30/16.
 */

public class PlayerTable implements Table {
    private static final String createOptionTable = "CREATE TABLE " + TABLE_NAME + "(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " VARCHAR(255)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createOptionTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
        onCreate(db);
    }

    @Override
    public void onDrop(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
    }

    public static class PlayerTableColumns implements de.spitak.amazinggame.db.base.TableColumns {

        public static final String TABLE_NAME = "player";
        public static final String NAME = "name";

        public static final String[] COLUMN_NAMES = new String[]{
                _ID, NAME
        };
    }
}
