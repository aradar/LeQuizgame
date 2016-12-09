package de.spitak.amazinggame.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by rschlett on 11/25/16.
 */

public class MauswieselSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mauswiesel.db";
    public static final int DATABASE_VERSION = 1;
    private static final String createGameTable = "CREATE TABLE " + GameColumns.TABLE_NAME + " (" +
            GameColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            GameColumns.NAME + " VARCHAR(255), " +
            GameColumns.DESCRIPTION + " VARCHAR(255), " +
            GameColumns.IMAGE + " VARCHAR(255));";
    private static final String createItemTable = "CREATE TABLE " + ItemColumns.TABLE_NAME + "(" +
            ItemColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ItemColumns.NAME + " VARCHAR(255), " +
            ItemColumns.DESCRIPTION + " VARCHAR(255), " +
            ItemColumns.IMAGE + " VARCHAR(255), " +
            ItemColumns.SMALL_IMAGE + " VARCHAR(255));";
    private static final String createOptionTable = "CREATE TABLE " + OptionColumns.TABLE_NAME + "(" +
            OptionColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            OptionColumns.GAME_ID + " INTEGER REFERENCES Game(_id), " +
            OptionColumns.TITLE + " VARCHAR(255), " +
            OptionColumns.DESCRIPTION + " VARCHAR(255), " +
            OptionColumns.HINT + " VARCHAR(255), " +
            OptionColumns.IMAGE + " VARCHAR(255), " +
            OptionColumns.PARENT_ID + " INTEGER REFERENCES Option(_id)," +
            OptionColumns.LEFT_CHILD_ID + " INTEGER REFERENCES Option(_id)," +
            OptionColumns.RIGHT_CHILD_ID + " INTEGER REFERENCES Option(_id)," +
            OptionColumns.LOOT_ID + " INTEGER REFERENCES Loot(_id)," +
            OptionColumns.REQUIREMENT_ID + " INTEGER REFERENCES Requirement(_id)," +
            OptionColumns.BACKSTEP_BLOCKED + " BOOLEAN," +
            OptionColumns.COMPLETED + " BOOLEAN);";
    private static final String createLootTable = "CREATE TABLE " + LootColumns.TABLE_NAME + "(" +
            LootColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            LootColumns.ITEM_ID + " INTEGER REFERENCES Item(_id), " +
            LootColumns.GAME_ID + " INTEGER REFERENCES Game(_id));";
    private static final String createRequirementTable = "CREATE TABLE " + RequirementColumns.TABLE_NAME + "(" +
            RequirementColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            RequirementColumns.ITEM_ID + " INTEGER REFERENCES Item(_id), " +
            RequirementColumns.GAME_ID + " INTEGER REFERENCES Game(_id));";

    public MauswieselSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createGameTable);
        db.execSQL(createItemTable);
        db.execSQL(createOptionTable);
        db.execSQL(createLootTable);
        db.execSQL(createRequirementTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MauswieselSQLiteOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + GameColumns.TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + ItemColumns.TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + OptionColumns.TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + LootColumns.TABLE_NAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + RequirementColumns.TABLE_NAME + ";");
        onCreate(db);
    }

    // TODO: 12/1/16 move the hole table code into a specified table class with a onCreate and onUpgrade function
    public static class GameColumns implements BaseColumns {
        public static final String TABLE_NAME = "game";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String IMAGE = "image";

        public static final String[] COLUMNS = new String[]{_ID, NAME, DESCRIPTION, IMAGE};
    }

    public static class ItemColumns implements BaseColumns {
        public static final String TABLE_NAME = "item";
        public static final String NAME = "name";
        public static final String DESCRIPTION = "description";
        public static final String IMAGE = "image";
        public static final String SMALL_IMAGE = "smallimage";

        public static final String[] COLUMNS = new String[]{_ID, NAME, DESCRIPTION, IMAGE, SMALL_IMAGE};
    }

    public static class OptionColumns implements BaseColumns {
        public static final String TABLE_NAME = "option";
        public static final String GAME_ID = "gameid";
        public static final String TITLE = "title";
        public static final String DESCRIPTION = "description";
        public static final String HINT = "hint";
        public static final String IMAGE = "image";
        public static final String PARENT_ID = "parentid";
        public static final String LEFT_CHILD_ID = "leftchildid";
        public static final String RIGHT_CHILD_ID = "rightchildid";
        public static final String LOOT_ID = "lootid";
        public static final String REQUIREMENT_ID = "requirementid";
        public static final String BACKSTEP_BLOCKED = "backstepblocked";
        public static final String COMPLETED = "completed";

        public static final String[] COLUMNS = new String[]{
                _ID, GAME_ID, TITLE, DESCRIPTION,
                HINT, IMAGE, PARENT_ID, LEFT_CHILD_ID,
                RIGHT_CHILD_ID, LOOT_ID, REQUIREMENT_ID,
                BACKSTEP_BLOCKED, COMPLETED
        };
    }

    public static class RequirementColumns implements BaseColumns {
        public static final String TABLE_NAME = "requirements";
        public static final String ITEM_ID = "itemid";
        public static final String GAME_ID = "gameid";

        public static final String[] COLUMNS = new String[]{_ID, ITEM_ID, GAME_ID};
    }

    public static class LootColumns implements BaseColumns {
        public static final String TABLE_NAME = "loot";
        public static final String ITEM_ID = "itemid";
        public static final String GAME_ID = "gameid";

        public static final String[] COLUMNS = new String[]{_ID, ITEM_ID, GAME_ID};
    }
}
