/*
package de.spitak.amazinggame;

import android.content.ContentProvider;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import io.realm.Realm;

*/
/**
 * Created by rschlett on 12/29/16.
 *//*




// TODO: 12/29/16 name has to be changed accordingly to the final name of the game
// TODO: 12/29/16 add a fucking contract class
// TODO: 12/30/16 add OptionTemplate and Player
public class HighScoreProvider extends ContentProvider {

    public static final String AUTHORITY = "de.spitak.amazinggame.HighScoreProvider";
    public static final String ALL_PATH = "all";
    public static final String TOP_PATH = "top/#";
    public static final String NAME_PATH = "name*/
/*";

    public static final String URL = "content://" + AUTHORITY;
    public static final Uri CONTENT_URI = Uri.parse(URL);

    private static final UriMatcher uriMatcher;

    private enum RequestType {ALL, TOP, NAME}

    private static final String[] columns = new String[] { "position", "name", "movesTaken" };

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, ALL_PATH, RequestType.ALL.ordinal());
        uriMatcher.addURI(AUTHORITY, TOP_PATH, RequestType.TOP.ordinal());
        uriMatcher.addURI(AUTHORITY, NAME_PATH, RequestType.NAME.ordinal());
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        Realm.init(context);
        return true; // should this be always true
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        RequestType requestType = RequestType.values()[uriMatcher.match(uri)];
        switch (requestType) {
            case ALL:
                try {
                    java.net.URL url = new URL("http://localhost:4567/all");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    String allHighScoreEntities = IOUtils.toString(connection.getInputStream());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

            case NAME:
                break;

            case TOP:
                break;
        }

        MatrixCursor matrixCursor = new MatrixCursor(columns);


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



}

*/
