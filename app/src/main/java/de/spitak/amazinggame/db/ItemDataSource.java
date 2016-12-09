package de.spitak.amazinggame.db;

import android.content.Context;
import android.database.Cursor;

import de.spitak.amazinggame.model.Item;

/**
 * Created by rschlett on 12/1/16.
 */

public class ItemDataSource extends BaseDataSource<Item> {

    private static final String TABLE_NAME = "Item";
    private static final String[] COLUMN_NAMES = {"_id", "name", "description",
            "image", "smallImage"};

    public ItemDataSource(Context context) {
        super(context);
    }

    @Override
    public Item getData(int id) {
        return null;
    }

    @Override
    public Item createData(Item data) {
        return null;
    }

    @Override
    public void deleteData(Item data) {

    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected Item cursorToData(Cursor cursor) {
        return new Item(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4));
    }
}
