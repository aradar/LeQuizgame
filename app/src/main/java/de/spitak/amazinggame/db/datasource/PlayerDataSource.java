package de.spitak.amazinggame.db.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import de.spitak.amazinggame.db.base.DataSource;
import de.spitak.amazinggame.model.Player;

/**
 * Created by rschlett on 12/30/16.
 */

// TODO: 12/30/16 implement this
public class PlayerDataSource extends DataSource<Player> {
    public PlayerDataSource(Context context) {
        super(context);
    }

    @Override
    protected String getTableName() {
        return null;
    }

    @Override
    protected String[] getColumnNames() {
        return new String[0];
    }

    @Override
    public Player cursorToEntity(Cursor cursor) {
        return null;
    }

    @Override
    public ContentValues entityToContentValues(Player row) {
        return null;
    }
}
