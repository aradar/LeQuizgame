package de.spitak.amazinggame.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import de.spitak.amazinggame.db.base.BaseDataSource;
import de.spitak.amazinggame.model.Game;

import static de.spitak.amazinggame.db.table.GameTable.GameTableColumns.*;

/**
 * Created by rschlett on 12/1/16.
 */

public class GameDataSource extends BaseDataSource<Game> {

    public GameDataSource(Context context) {
        super(context);
    }

    @Override
    public Game getData(int id) {
        Cursor cursor = db.query(
                TABLE_NAME,
                COLUMN_NAMES,
                _ID + "=" + id,
                null,
                null, null, null);
        Game newGame = cursorToData(cursor);
        cursor.close();
        return newGame;
    }

    @Override
    public Game createData(Game data) {
        ContentValues values = new ContentValues();
        values.put(NAME, data.getName());
        values.put(DESCRIPTION, data.getDescription());
        values.put(IMAGE, data.getImage());
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
        Game newGame = cursorToData(cursor);
        cursor.close();
        return newGame;
    }

    @Override
    public void deleteData(Game data) {
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
    protected Game cursorToData(Cursor cursor) {
        return new Game(
                cursor.getInt(0), // _ID
                cursor.getString(1), // NAME
                cursor.getString(2), // DESCRIPTION
                cursor.getString(3)); // IMAGE
    }
}
