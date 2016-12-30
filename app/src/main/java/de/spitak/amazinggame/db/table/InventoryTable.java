package de.spitak.amazinggame.db.table;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import de.spitak.amazinggame.db.base.Table;

import static android.provider.BaseColumns._ID;
import static de.spitak.amazinggame.db.table.InventoryTable.InventoryTableColumns.ITEM_ID;
import static de.spitak.amazinggame.db.table.InventoryTable.InventoryTableColumns.PLAYER_ID;
import static de.spitak.amazinggame.db.table.InventoryTable.InventoryTableColumns.TABLE_NAME;

/**
 * Created by rschlett on 12/30/16.
 */

public class InventoryTable implements Table {
    private static final String createItemTable = "CREATE TABLE " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PLAYER_ID + " INTEGER REFERENCES player(_ID), " +
            ITEM_ID + " INTEGER REFERENCES item(_ID);";

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

    public static class InventoryTableColumns implements de.spitak.amazinggame.db.base.TableColumns {

        public static final String TABLE_NAME = "Inventory";

        public static final String PLAYER_ID = "player_id";
        public static final String ITEM_ID = "item_id";

        public static final String[] COLUMN_NAMES = {_ID, PLAYER_ID, ITEM_ID};
    }
}
