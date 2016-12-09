package de.spitak.amazinggame.db.table;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import de.spitak.amazinggame.db.base.Table;

import static de.spitak.amazinggame.db.table.RequirementTable.RequirementTableColumns.*;

/**
 * Created by dephiloper on 09.12.16.
 */
public class RequirementTable implements Table {
    private static final String createRequirementTable = "CREATE TABLE " + TABLE_NAME + "(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ITEM_ID + " INTEGER REFERENCES Item(_id), " +
            GAME_ID + " INTEGER REFERENCES Game(_id));";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createRequirementTable);
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

    public static class RequirementTableColumns implements de.spitak.amazinggame.db.base.TableColumns {
        public static final String TABLE_NAME = "requirement";

        public static final String ITEM_ID = "itemid";
        public static final String GAME_ID = "gameid";

        public static final String[] COLUMN_NAMES = new String[]{_ID, ITEM_ID, GAME_ID};
    }
}
