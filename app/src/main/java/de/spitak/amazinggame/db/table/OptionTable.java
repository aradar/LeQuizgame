package de.spitak.amazinggame.db.table;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import de.spitak.amazinggame.db.base.Table;

import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.BACKSTEP_BLOCKED;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.COMPLETED;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.DESCRIPTION;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.GAME_ID;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.HINT;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.IMAGE;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.LEFT_CHILD_ID;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.LOOT_ID;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.PARENT_ID;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.PLAYER_ID;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.REQUIREMENT_ID;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.RIGHT_CHILD_ID;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.TABLE_NAME;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.TITLE;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns._ID;

/**
 * Created by dephiloper on 09.12.16.
 */

public class OptionTable implements Table {
    private static final String createOptionTable = "CREATE TABLE " + TABLE_NAME + "(" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PLAYER_ID + " INTEGER REFERENCES Player(_id)" +
            GAME_ID + " INTEGER REFERENCES Game(_id), " +
            TITLE + " VARCHAR(255), " +
            DESCRIPTION + " VARCHAR(255), " +
            HINT + " VARCHAR(255), " +
            IMAGE + " VARCHAR(255), " +
            PARENT_ID + " INTEGER REFERENCES Option(_id)," +
            LEFT_CHILD_ID + " INTEGER REFERENCES Option(_id)," +
            RIGHT_CHILD_ID + " INTEGER REFERENCES Option(_id)," +
            LOOT_ID + " INTEGER REFERENCES Loot(_id)," +
            REQUIREMENT_ID + " INTEGER REFERENCES Requirement(_id)," +
            BACKSTEP_BLOCKED + " BOOLEAN," +
            COMPLETED + " BOOLEAN);";


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

    public static class OptionTableColumns implements de.spitak.amazinggame.db.base.TableColumns {

        public static final String TABLE_NAME = "option";
        public static final String PLAYER_ID = "player_id";
        public static final String GAME_ID = "game_id";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String HINT = "hint";
        public static final String IMAGE = "image";
        public static final String PARENT_ID = "parent_id";
        public static final String LEFT_CHILD_ID = "left_child_id";
        public static final String RIGHT_CHILD_ID = "right_child_id";
        public static final String LOOT_ID = "loot_id";
        public static final String REQUIREMENT_ID = "requirement_id";
        public static final String BACKSTEP_BLOCKED = "backstep_blocked";
        public static final String COMPLETED = "completed";

        public static final String[] COLUMN_NAMES = new String[]{
                _ID, GAME_ID, TITLE, DESCRIPTION,
                HINT, IMAGE, PARENT_ID, LEFT_CHILD_ID,
                RIGHT_CHILD_ID, LOOT_ID, REQUIREMENT_ID,
                BACKSTEP_BLOCKED, COMPLETED
        };
    }
}
