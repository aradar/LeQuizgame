package de.spitak.amazinggame.db;

import android.content.Context;
import android.database.Cursor;

import de.spitak.amazinggame.model.Option;

/**
 * Created by rschlett on 12/1/16.
 */

public class OptionDataSource extends BaseDataSource<Option> {

    private final static String TABLE_NAME = "Option";
    private final static String[] COLUMN_NAMES = {"_id", "title", "description", "hint",
            "image", "parentChildId", "leftChildId", "rightChildId", "backstepBlocked", "completed"};

    public OptionDataSource(Context context) {
        super(context);
    }

    @Override
    public Option getData(int id) {
        return null;
    }

    @Override
    public Option createData(Option data) {
        return null;
    }

    @Override
    public void deleteData(Option data) {

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
    protected Option cursorToData(Cursor cursor) {
        return new Option(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5),
                cursor.getInt(6), cursor.getInt(7), cursor.getInt(9) == 1, cursor.getInt(9) == 1);
    }
}
