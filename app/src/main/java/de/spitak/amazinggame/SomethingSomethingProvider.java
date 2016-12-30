package de.spitak.amazinggame;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

import de.spitak.amazinggame.db.MauswieselSQLiteOpenHelper;
import de.spitak.amazinggame.db.base.DataSource;
import de.spitak.amazinggame.db.base.Entity;
import de.spitak.amazinggame.db.datasource.GameDataSource;
import de.spitak.amazinggame.db.datasource.ItemDataSource;
import de.spitak.amazinggame.db.datasource.LootDataSource;
import de.spitak.amazinggame.db.datasource.OptionDataSource;
import de.spitak.amazinggame.db.datasource.RequirementDataSource;
import de.spitak.amazinggame.model.Game;
import de.spitak.amazinggame.model.Item;
import de.spitak.amazinggame.model.Loot;
import de.spitak.amazinggame.model.Option;
import de.spitak.amazinggame.model.Requirement;

/**
 * Created by rschlett on 12/29/16.
 */

// TODO: 12/29/16 name has to be changed accordingly to the final name of the game
// TODO: 12/29/16 add a fucking contract class
// TODO: 12/30/16 add OptionTemplate and Player
public class SomethingSomethingProvider extends ContentProvider {

    public static final String AUTHORITY = "de.spitak.amazinggame.SomethingSomethingProvider";
    public static final String URL = "content://" + AUTHORITY;
    public static final String GAMES_PATH = "games";
    public static final String ITEMS_PATH = "items";
    public static final String LOOT_PATH = "loot";
    public static final String OPTIONS_PATH = "options";
    public static final String REQUIREMENTS_PATH = "requirements";
    public static final Uri CONTENT_URI = Uri.parse(URL);

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, GAMES_PATH, RequestType.MULTIPLE_ROWS.ordinal());
        uriMatcher.addURI(AUTHORITY, GAMES_PATH + "/#", RequestType.SINGLE_ROW.ordinal());
        uriMatcher.addURI(AUTHORITY, ITEMS_PATH, RequestType.MULTIPLE_ROWS.ordinal());
        uriMatcher.addURI(AUTHORITY, ITEMS_PATH + "/#", RequestType.SINGLE_ROW.ordinal());
        uriMatcher.addURI(AUTHORITY, LOOT_PATH, RequestType.MULTIPLE_ROWS.ordinal());
        uriMatcher.addURI(AUTHORITY, LOOT_PATH + "/#", RequestType.SINGLE_ROW.ordinal());
        uriMatcher.addURI(AUTHORITY, OPTIONS_PATH, RequestType.MULTIPLE_ROWS.ordinal());
        uriMatcher.addURI(AUTHORITY, OPTIONS_PATH + "/#", RequestType.SINGLE_ROW.ordinal());
        uriMatcher.addURI(AUTHORITY, REQUIREMENTS_PATH, RequestType.MULTIPLE_ROWS.ordinal());
        uriMatcher.addURI(AUTHORITY, REQUIREMENTS_PATH + "/#", RequestType.SINGLE_ROW.ordinal());
    }

    private GameDataSource gameDataSource;
    private ItemDataSource itemDataSource;
    private LootDataSource lootDataSource;
    private OptionDataSource optionDataSource;
    private RequirementDataSource requirementDataSource;

    @Override
    public boolean onCreate() {
        Context context = getContext();
        MauswieselSQLiteOpenHelper sqLiteOpenHelper = new MauswieselSQLiteOpenHelper(context);
        gameDataSource = new GameDataSource(context);
        itemDataSource = new ItemDataSource(context);
        lootDataSource = new LootDataSource(context);
        optionDataSource = new OptionDataSource(context);
        requirementDataSource = new RequirementDataSource(context);

        return sqLiteOpenHelper.getWritableDatabase() != null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        Cursor cursor = null;
        DataSource<?> dataSource = getDataSourceByPathSegment(uri.getPathSegments().get(0));
        if (dataSource != null) {
            RequestType requestType = RequestType.values()[uriMatcher.match(uri)];
            switch (requestType) {
                case SINGLE_ROW:
                    cursor = dataSource.query(projection, getIdFromUri(uri, 1), sortOrder);
                    break;

                case MULTIPLE_ROWS:
                    cursor = dataSource.query(projection, selection, selectionArgs, sortOrder);
                    break;
            }
        }

        return cursor;
    }

    // TODO: 12/30/16 the mimeType has to be also changed based on the final game name
    @Override
    public String getType(Uri uri) {
        String prefix;
        String suffix;
        RequestType requestType = RequestType.values()[uriMatcher.match(uri)];
        switch (requestType) {
            case SINGLE_ROW:
                prefix = "vnd.android.cursor.item";
                break;

            case MULTIPLE_ROWS:
                prefix = "vnd.android.cursor.dir";
                break;

            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }

        suffix = uri.getPathSegments().get(0);
        return prefix + "/vnd.amazinggame." + suffix;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        RequestType requestType = RequestType.values()[uriMatcher.match(uri)];
        DataSource<?> dataSource = getDataSourceByPathSegment(uri.getPathSegments().get(0));
        Uri returnUri = null;
        if (dataSource != null && requestType == RequestType.MULTIPLE_ROWS) {
            long newId = dataSource.insert(values);
            Cursor cursor = dataSource.query(null, newId, null);
            cursor.moveToFirst();
            Entity entity = dataSource.cursorToEntity(cursor);
            cursor.close();
            returnUri = entityToUri(entity);
        }

        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int affectedRows = 0;
        DataSource<?> dataSource = getDataSourceByPathSegment(uri.getPathSegments().get(0));
        if (dataSource != null) {
            RequestType requestType = RequestType.values()[uriMatcher.match(uri)];
            switch (requestType) {
                case SINGLE_ROW:
                    affectedRows = dataSource.delete(getIdFromUri(uri, 1));
                    break;

                case MULTIPLE_ROWS:
                    affectedRows = dataSource.delete(selection, selectionArgs);
                    break;
            }
        }

        return affectedRows;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int affectedRows = 0;
        DataSource<?> dataSource = getDataSourceByPathSegment(uri.getPathSegments().get(0));
        if (dataSource != null) {
            RequestType requestType = RequestType.values()[uriMatcher.match(uri)];
            switch (requestType) {
                case SINGLE_ROW:
                    affectedRows = dataSource.update(values, getIdFromUri(uri, 1));
                    break;

                case MULTIPLE_ROWS:
                    affectedRows = dataSource.update(values, selection, selectionArgs);
                    break;
            }
        }

        return affectedRows;
    }

    @Nullable
    private Uri entityToUri(Entity entity) {
        if (entity instanceof Game) {
            return Uri.parse(URL + "/" + GAMES_PATH);
        } else if (entity instanceof Item) {
            return Uri.parse(URL + "/" + ITEMS_PATH);
        } else if (entity instanceof Loot) {
            return Uri.parse(URL + "/" + LOOT_PATH);
        } else if (entity instanceof Option) {
            return Uri.parse(URL + "/" + OPTIONS_PATH);
        } else if (entity instanceof Requirement) {
            return Uri.parse(URL + "/" + REQUIREMENTS_PATH);
        }

        return null;
    }

    // TODO: 12/29/16 should this be refactored? (change to stringToLong)
    private long getIdFromUri(Uri uri, int pathSegmentPosition) {
        try {
            String idString = uri.getPathSegments().get(pathSegmentPosition);
            return Long.valueOf(idString);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    @Nullable
    private DataSource<?> getDataSourceByPathSegment(String pathSegment) {
        switch (pathSegment) {
            case GAMES_PATH:
                return gameDataSource;

            case ITEMS_PATH:
                return itemDataSource;

            case LOOT_PATH:
                return lootDataSource;

            case OPTIONS_PATH:
                return optionDataSource;

            case REQUIREMENTS_PATH:
                return requirementDataSource;
        }

        return null;
    }

    private enum RequestType {SINGLE_ROW, MULTIPLE_ROWS}
}
