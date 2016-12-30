package de.spitak.amazinggame.db.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import de.spitak.amazinggame.db.base.DataSource;
import de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns;
import de.spitak.amazinggame.model.Option;

/**
 * Created by rschlett on 12/1/16.
 */

public class OptionDataSource extends DataSource<Option> {

    public OptionDataSource(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return OptionTableColumns.TABLE_NAME;
    }

    @Override
    protected String[] getColumnNames() {
        return OptionTableColumns.COLUMN_NAMES;
    }

    @Override
    public Option cursorToEntity(Cursor cursor) {
        return new Option(
                cursor.getInt(0), // _ID
                cursor.getInt(1), // GAME_ID
                cursor.getString(2), // TITLE
                cursor.getString(3), // DESCRIPTION
                cursor.getString(4), // HINT
                cursor.getString(5), // IMAGE
                cursor.getInt(6), // PARENT_ID
                cursor.getInt(7), // LEFT_CHILD_ID
                cursor.getInt(8), // RIGHT_CHILD_ID
                cursor.getInt(9) == 1, // BACKSTEP_BLOCKED
                cursor.getInt(10) == 1 // COMPLETED
        );
    }

    @Override
    public ContentValues entityToContentValues(Option row) {
        ContentValues values = new ContentValues();
        values.put(OptionTableColumns.TITLE, row.getTitle());
        values.put(OptionTableColumns.DESCRIPTION, row.getDescription());
        values.put(OptionTableColumns.HINT, row.getHint());
        values.put(OptionTableColumns.IMAGE, row.getImage());
        values.put(OptionTableColumns.PARENT_ID, row.getParentId());
        values.put(OptionTableColumns.LEFT_CHILD_ID, row.getLeftChildId());
        values.put(OptionTableColumns.RIGHT_CHILD_ID, row.getRightChildId());
        values.put(OptionTableColumns.BACKSTEP_BLOCKED, row.getBackstepBlocked());
        values.put(OptionTableColumns.COMPLETED, row.getCompleted());
        return values;
    }
}
