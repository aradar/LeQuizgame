package de.spitak.amazinggame.db.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import de.spitak.amazinggame.db.base.DataSource;
import de.spitak.amazinggame.db.table.RequirementTable.RequirementTableColumns;
import de.spitak.amazinggame.model.Requirement;

/**
 * Created by dephiloper on 20.12.16.
 */
public class RequirementDataSource extends DataSource<Requirement> {

    public RequirementDataSource(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return RequirementTableColumns.TABLE_NAME;
    }

    @Override
    protected String[] getColumnNames() {
        return RequirementTableColumns.COLUMN_NAMES;
    }

    @Override
    public Requirement cursorToEntity(Cursor cursor) {
        return new Requirement(
                cursor.getInt(0), // _ID
                cursor.getInt(1), // ITEM_ID
                cursor.getInt(2) // GAME_ID
        );
    }

    @Override
    public ContentValues entityToContentValues(Requirement row) {
        ContentValues values = new ContentValues();
        values.put(RequirementTableColumns.ITEM_ID, row.getItemId());
        values.put(RequirementTableColumns.GAME_ID, row.getGameId());
        return values;
    }
}
