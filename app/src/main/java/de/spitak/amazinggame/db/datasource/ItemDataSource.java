package de.spitak.amazinggame.db.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import de.spitak.amazinggame.db.base.BaseDataSource;
import de.spitak.amazinggame.model.Item;

import static android.provider.BaseColumns._ID;
import static de.spitak.amazinggame.db.table.ItemTable.*;
import static de.spitak.amazinggame.db.table.ItemTable.ItemTableColumns.*;

/**
 * Created by rschlett on 12/1/16.
 */

public class ItemDataSource extends BaseDataSource<Item> {

    public ItemDataSource(Context context) {
        super(context);
    }

    @Override
    public Item getData(int id) {
        Cursor cursor = db.query(
                TABLE_NAME,
                COLUMN_NAMES,
                _ID + "=" + id,
                null,
                null, null, null);
        Item item = cursorToData(cursor);
        cursor.close();
        return item;
    }

    @Override
    public Item createData(Item data) {
        ContentValues values = new ContentValues();
        values.put(NAME, data.getName());
        values.put(DESCRIPTION, data.getDescription());
        values.put(IMAGE, data.getImage());
        values.put(SMALL_IMAGE, data.getSmallImage());
        long insertId = db.insert(
                TABLE_NAME,
                null,
                values);

        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                _ID + " = " + insertId,
                null,
                null, null, null);
        cursor.moveToFirst();
        Item item = cursorToData(cursor);
        cursor.close();
        return item;
    }

    @Override
    public void deleteData(Item data) {
        if (data.getId() > 0) {
            db.delete(getTableName(), _ID + "=" + data.getId(), null);
        }
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
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
