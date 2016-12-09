package de.spitak.amazinggame.db.table;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import de.spitak.amazinggame.db.base.Table;

import static de.spitak.amazinggame.db.table.ItemTable.ItemTableColumns.*;

/**
 * Created by dephiloper on 09.12.16.
 */

public class ItemTable implements Table {
    private static final String createItemTable = "CREATE TABLE " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " VARCHAR(255), " +
            DESCRIPTION + " VARCHAR(255), " +
            IMAGE + " VARCHAR(255), " +
            SMALL_IMAGE + " VARCHAR(255));";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createItemTable);
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

    public static class ItemTableColumns implements de.spitak.amazinggame.db.base.TableColumns {

        public static final String TABLE_NAME = "Item";

        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String IMAGE = "image";
        public static final String SMALL_IMAGE = "smallimage";

        public static final String[] COLUMN_NAMES = {_ID, NAME, DESCRIPTION,
                IMAGE, SMALL_IMAGE};
    }
}
