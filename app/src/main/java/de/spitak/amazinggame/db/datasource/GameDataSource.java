package de.spitak.amazinggame.db.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import de.spitak.amazinggame.db.base.DataSource;
import de.spitak.amazinggame.db.table.GameTable;
import de.spitak.amazinggame.model.Game;

/**
 * Created by rschlett on 12/1/16.
 */

public class GameDataSource extends DataSource<Game> {

    public GameDataSource(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return GameTable.GameTableColumns.TABLE_NAME;
    }

    @Override
    protected String[] getColumnNames() {
        return GameTable.GameTableColumns.COLUMN_NAMES;
    }

    @Override
    public Game cursorToEntity(Cursor cursor) {
        return new Game(
                cursor.getInt(0), // _ID
                cursor.getString(1), // NAME
                cursor.getString(2), // DESCRIPTION
                cursor.getString(3) // IMAGE
        );
    }

    @Override
    public ContentValues entityToContentValues(Game row) {
        ContentValues values = new ContentValues();
        values.put(GameTable.GameTableColumns.NAME, row.getName());
        values.put(GameTable.GameTableColumns.DESCRIPTION, row.getDescription());
        values.put(GameTable.GameTableColumns.IMAGE, row.getImage());
        return values;
    }
}
