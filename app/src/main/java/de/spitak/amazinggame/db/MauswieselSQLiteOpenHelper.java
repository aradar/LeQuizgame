package de.spitak.amazinggame.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import de.spitak.amazinggame.db.base.Table;
import de.spitak.amazinggame.db.table.GameTable;
import de.spitak.amazinggame.db.table.ItemTable;
import de.spitak.amazinggame.db.table.LootTable;
import de.spitak.amazinggame.db.table.OptionTable;
import de.spitak.amazinggame.db.table.RequirementTable;

/**
 * Created by rschlett on 11/25/16.
 */

public class MauswieselSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mauswiesel.db";
    public static final int DATABASE_VERSION = 1;
    public Table[] TableArray = new Table[] {new GameTable(), new ItemTable(), new OptionTable(),
    new LootTable(), new RequirementTable()};

    public MauswieselSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (Table t : TableArray)
            t.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MauswieselSQLiteOpenHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        for (Table t : TableArray)
            t.onDrop(db);
        onCreate(db);
    }
}
