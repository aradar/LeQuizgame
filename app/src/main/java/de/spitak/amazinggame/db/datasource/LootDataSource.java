package de.spitak.amazinggame.db.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import de.spitak.amazinggame.db.base.DataSource;
import de.spitak.amazinggame.db.table.LootTable.LootTableColumns;
import de.spitak.amazinggame.model.Loot;

/**
 * Created by dephiloper on 20.12.16.
 */

public class LootDataSource extends DataSource<Loot> {

    public LootDataSource(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return LootTableColumns.TABLE_NAME;
    }

    @Override
    protected String[] getColumnNames() {
        return LootTableColumns.COLUMN_NAMES;
    }

    @Override
    public Loot cursorToEntity(Cursor cursor) {
        return new Loot(
                cursor.getInt(0), // _ID
                cursor.getInt(1), // ITEM_ID
                cursor.getInt(2) // GAME_ID
        );
    }

    @Override
    public ContentValues entityToContentValues(Loot row) {
        ContentValues values = new ContentValues();
        values.put(LootTableColumns.ITEM_ID, row.getItemId());
        values.put(LootTableColumns.GAME_ID, row.getGameId());
        return values;
    }
}
