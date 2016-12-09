package de.spitak.amazinggame.db.base;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;

import de.spitak.amazinggame.db.MauswieselSQLiteOpenHelper;

/**
 * Created by rschlett on 12/1/16.
 */

public abstract class BaseDataSource<T> {

    protected SQLiteDatabase db;
    protected MauswieselSQLiteOpenHelper dbHelper;

    public BaseDataSource(Context context) {
        dbHelper = new MauswieselSQLiteOpenHelper(context);
    }

    protected abstract String getTableName();

    protected abstract String[] getColumnNames();

    public void open() throws SQLiteException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public List<T> getAllData() {
        List<T> allData = new ArrayList<>();

        Cursor cursor = db.query(getTableName(),
                getColumnNames(), null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            T data = cursorToData(cursor);
            allData.add(data);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return allData;
    }

    public abstract T getData(int id);

    public abstract T createData(T data);

    public abstract void deleteData(T data);

    protected abstract T cursorToData(Cursor cursor);
}
