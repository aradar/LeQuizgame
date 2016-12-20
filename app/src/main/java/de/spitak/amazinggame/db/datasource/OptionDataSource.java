package de.spitak.amazinggame.db.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import de.spitak.amazinggame.db.base.BaseDataSource;
import de.spitak.amazinggame.db.table.GameTable;
import de.spitak.amazinggame.model.Game;
import de.spitak.amazinggame.model.Option;

import static android.provider.BaseColumns._ID;
import static de.spitak.amazinggame.db.table.GameTable.GameTableColumns.DESCRIPTION;
import static de.spitak.amazinggame.db.table.GameTable.GameTableColumns.IMAGE;
import static de.spitak.amazinggame.db.table.GameTable.GameTableColumns.NAME;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.BACKSTEP_BLOCKED;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.COMPLETED;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.HINT;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.LEFT_CHILD_ID;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.PARENT_ID;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.RIGHT_CHILD_ID;
import static de.spitak.amazinggame.db.table.OptionTable.OptionTableColumns.TITLE;

/**
 * Created by rschlett on 12/1/16.
 */

public class OptionDataSource extends BaseDataSource<Option> {

    private final static String TABLE_NAME = "Option";
    private final static String[] COLUMN_NAMES = {"_id", "title", "description", "hint",
            "image", "parentId", "leftChildId", "rightChildId", "backstepBlocked", "completed"};

    public OptionDataSource(Context context) {
        super(context);
    }

    @Override
    public Option getData(int id) {
        Cursor cursor = db.query(
                TABLE_NAME,
                COLUMN_NAMES,
                _ID + "=" + id,
                null,
                null, null, null);
        Option option = cursorToData(cursor);
        cursor.close();
        return option;
    }

    @Override
    public Option createData(Option data) {
        ContentValues values = new ContentValues();
        values.put(TITLE,data.getTitle());
        values.put(DESCRIPTION, data.getDescription());
        values.put(HINT, data.getHint());
        values.put(IMAGE, data.getImage());
        values.put(PARENT_ID, data.getParentId());
        values.put(LEFT_CHILD_ID, data.getLeftChildId());
        values.put(RIGHT_CHILD_ID, data.getRightChildId());
        values.put(BACKSTEP_BLOCKED, data.getBackstepBlocked());
        values.put(COMPLETED, data.getCompleted());

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
        Option option = cursorToData(cursor);
        cursor.close();
        return option;
    }

    @Override
    public void deleteData(Option data) {
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
        return COLUMN_NAMES;
    }

    @Override
    protected Option cursorToData(Cursor cursor) {
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
                cursor.getInt(10) == 1); // COMPLETED
    }
}
