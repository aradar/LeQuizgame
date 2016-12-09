package de.spitak.amazinggame.db;

import android.content.Context;
import android.database.Cursor;

import de.spitak.amazinggame.db.base.BaseDataSource;
import de.spitak.amazinggame.db.table.ItemTable;
import de.spitak.amazinggame.model.Item;

import static de.spitak.amazinggame.db.table.ItemTable.*;

/**
 * Created by rschlett on 12/1/16.
 */

public class ItemDataSource extends BaseDataSource<Item> {

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
        return ItemTableColumns.TABLE_NAME;
    }

    @Override
    protected String[] getColumnNames() {
        return ItemTableColumns.COLUMN_NAMES;
    }

    @Override
    protected Item cursorToData(Cursor cursor) {
        return new Item(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4));
    }
}
