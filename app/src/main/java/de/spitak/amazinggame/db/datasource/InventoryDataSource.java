package de.spitak.amazinggame.db.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import de.spitak.amazinggame.db.base.DataSource;
import de.spitak.amazinggame.model.Inventory;

/**
 * Created by rschlett on 12/30/16.
 */

public class InventoryDataSource extends DataSource<Inventory> {
    public InventoryDataSource(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected String[] getColumnNames() {
        return new String[0];
    }

    @Override
    public Inventory cursorToEntity(Cursor cursor) {
        return null;
    }

    @Override
    public ContentValues entityToContentValues(Inventory row) {
        return null;
    }
}
