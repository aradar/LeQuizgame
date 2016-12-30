package de.spitak.amazinggame.db.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import de.spitak.amazinggame.db.MauswieselSQLiteOpenHelper;

/**
 * Created by rschlett on 12/1/16.
 */

// TODO: 12/29/16 implement more sophisticated query and update methods
public abstract class DataSource<T extends Entity> {

    // should this be switched to a static variable? are there any problems which this could cause?
    protected SQLiteDatabase db;
    protected MauswieselSQLiteOpenHelper dbHelper;

    public DataSource(Context context) {
        dbHelper = new MauswieselSQLiteOpenHelper(context);
        open();
    }

    protected abstract String getTableName();

    protected abstract String[] getColumnNames();

    public void open() throws SQLiteException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public Cursor query(String[] columns, long id, String orderBy) {
        return query(
                columns,
                "_ID = ?",
                new String[]{Long.toString(id)},
                orderBy
        );
    }

    public Cursor query(String[] columns, String selection, String[] selectionArgs,
                        String orderBy) {
        return db.query(
                getTableName(),
                columns,
                selection,
                selectionArgs,
                null,
                null,
                orderBy
        );
    }

    public int delete(long id) {
        int affectedRows = 0;
        if (id > 0) {
            affectedRows = delete("_ID = ?", new String[]{Long.toString(id)});
        }

        return affectedRows;
    }

    public int delete(String whereClause, String[] whereArgs) {
        return db.delete(getTableName(), whereClause, whereArgs);
    }

    public long insert(ContentValues values) {
        return db.insert(getTableName(), null, values);
    }

    public int update(ContentValues values, long id) {
        return update(values, "_ID = ?", new String[]{Long.toString(id)});
    }

    public int update(ContentValues values, String whereClause, String[] whereArgs) {
        return db.update(getTableName(), values, whereClause, whereArgs);
    }

    public abstract T cursorToEntity(Cursor cursor);

    public abstract ContentValues entityToContentValues(T row);
}
