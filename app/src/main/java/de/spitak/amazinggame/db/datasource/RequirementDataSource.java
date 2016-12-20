package de.spitak.amazinggame.db.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import de.spitak.amazinggame.db.base.BaseDataSource;
import de.spitak.amazinggame.model.Requirement;

import static de.spitak.amazinggame.db.table.RequirementTable.RequirementTableColumns.*;

/**
 * Created by dephiloper on 20.12.16.
 */
public class RequirementDataSource extends BaseDataSource<Requirement> {

    public RequirementDataSource(Context context) {
        super(context);
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
    public Requirement getData(int id) {
        Cursor cursor = db.query(
                TABLE_NAME,
                COLUMN_NAMES,
                _ID + "=" + id,
                null,
                null, null, null);
        Requirement requirement = cursorToData(cursor);
        cursor.close();
        return requirement;

    }

    @Override
    public Requirement createData(Requirement data) {
        ContentValues values = new ContentValues();
        values.put(ITEM_ID, data.getItemId());
        values.put(GAME_ID, data.getGameId());

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
        Requirement requirement = cursorToData(cursor);
        cursor.close();
        return requirement;
    }

    @Override
    public void deleteData(Requirement data) {
        if (data.getId() > 0) {
            db.delete(getTableName(), _ID + "=" + data.getId(), null);
        }
    }

    @Override
    protected Requirement cursorToData(Cursor cursor) {
        return new Requirement(
                cursor.getInt(0), // _ID
                cursor.getInt(1), // ITEM_ID
                cursor.getInt(2)); // GAME_ID
    }
}
