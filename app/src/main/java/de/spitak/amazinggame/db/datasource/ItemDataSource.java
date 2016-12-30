package de.spitak.amazinggame.db.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import de.spitak.amazinggame.db.base.DataSource;
import de.spitak.amazinggame.db.table.ItemTable.ItemTableColumns;
import de.spitak.amazinggame.model.Item;

/**
 * Created by rschlett on 12/1/16.
 */

public class ItemDataSource extends DataSource<Item> {

    public ItemDataSource(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return ItemTableColumns.TABLE_NAME;
    }

    @Override
    protected String[] getColumnNames() {
        return ItemTableColumns.COLUMN_NAMES;
    }

    @Override
    public Item cursorToEntity(Cursor cursor) {
        return new Item(
                cursor.getInt(0), // _ID
                cursor.getString(1), // NAME
                cursor.getString(2), // DESCRIPTION
                cursor.getString(3), // IMAGE
                cursor.getString(4) // SMALL_IMAGE
        );
    }

    @Override
    public ContentValues entityToContentValues(Item row) {
        ContentValues values = new ContentValues();
        values.put(ItemTableColumns.NAME, row.getName());
        values.put(ItemTableColumns.DESCRIPTION, row.getDescription());
        values.put(ItemTableColumns.IMAGE, row.getImage());
        values.put(ItemTableColumns.SMALL_IMAGE, row.getSmallImage());
        return values;
    }
}
