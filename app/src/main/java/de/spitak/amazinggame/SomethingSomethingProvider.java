package de.spitak.amazinggame;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.orm.query.Select;

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

    @Override
    public boolean onCreate() {
        Context context = getContext();
        SugarContext.init(context);

        return true; // should this be always true
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        Class type = getType(uri.getPathSegments().get(0));
        return Select.from(type)
                .where(selection, selectionArgs)
                .getCursor();
    }

    private Class getType(String pathSegment) {
        Class type = null;
        switch (pathSegment) {
            case GAMES_PATH:
                type = Game.class;
                break;

            case ITEMS_PATH:
                type = Item.class;
                break;

            case LOOT_PATH:
                type = Loot.class;
                break;

            case OPTIONS_PATH:
                type = Option.class;
                break;

            case REQUIREMENTS_PATH:
                type = Requirement.class;
                break;
        }
        return type;
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
        Class type = getType(uri.getPathSegments().get(0));
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Class type = getType(uri.getPathSegments().get(0));
        return SugarRecord.deleteAll(type, selection, selectionArgs);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return -1;
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

    private enum RequestType {SINGLE_ROW, MULTIPLE_ROWS}

}
