package de.spitak.amazinggame;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import de.spitak.amazinggame.model.HighScore;
import io.realm.Realm;

public class HighScoreProvider extends ContentProvider {

    public static final String AUTHORITY = "de.spitak.amazinggame.HighScoreProvider";

    public static final String URL = "content://" + AUTHORITY;
    public static final Uri CONTENT_URI = Uri.parse(URL);
    public static final String SERVER_URL = "http://144.76.252.196:45136/";
    private static final UriMatcher uriMatcher;
    private static final String[] columns = new String[] {"_id", "position", "name", "movesTaken" };

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, RequestType.ALL.cpPath, RequestType.ALL.ordinal());
        uriMatcher.addURI(AUTHORITY, RequestType.TOP.cpPath, RequestType.TOP.ordinal());
        uriMatcher.addURI(AUTHORITY, RequestType.NAME.cpPath, RequestType.NAME.ordinal());
        uriMatcher.addURI(AUTHORITY, RequestType.ADD.cpPath, RequestType.ADD.ordinal());
    }

    public HighScoreProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        RequestType requestType = RequestType.values()[uriMatcher.match(uri)];

        if (requestType == RequestType.ADD)
            try {
                if (putHighscore(RequestType.ADD.webURL, HighScore.toHighScore(values)))
                    return Uri.parse(AUTHORITY+RequestType.NAME.cpPath);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        else
            Log.e("Uri insert: ","Wrong Uri use add."); //// TODO: 12.01.17 Exception useful?

        return null;
    }

    @Override
    public boolean onCreate() {
        Context context = getContext();
        Realm.init(context);
        return true; // should this be always true
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
            RequestType requestType = RequestType.values()[uriMatcher.match(uri)]; //// TODO: 13.01.17 check if not null
        HighScore[] highScores = null;
        switch (requestType) {
            case ALL:
                    highScores = requestHighscores(RequestType.ALL.webURL);
                break;

            case NAME:
                    highScores = requestHighscores(RequestType.NAME.webURL + uri.getLastPathSegment());
                break;

            case TOP:
                    highScores = requestHighscores(RequestType.TOP.webURL + uri.getLastPathSegment());
                break;
        }

        MatrixCursor matrixCursor = new MatrixCursor(columns);
        if (highScores != null) {
            for (int i = 0; i < highScores.length; i++) {
                matrixCursor.addRow(new Object[]{i, highScores[i].getPosition(),
                        highScores[i].getName(), highScores[i].getMovesTaken()});
            }
        }

        return matrixCursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private HighScore[] requestHighscores(String url) {
        RequestQueue queue = Volley.newRequestQueue(getContext());
        RequestFuture<JSONArray> future = RequestFuture.newFuture();
        JsonArrayRequest request = new JsonArrayRequest(url,future,future);
        queue.add(request);
        JSONArray response = null;

        try {
            response = future.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        if (response != null)
            return new Gson().fromJson(response.toString(),HighScore[].class);
        else
            return null;
    }

    private boolean putHighscore(String url, HighScore highScore) throws JSONException {

        RequestQueue queue = Volley.newRequestQueue(getContext());
        RequestFuture<JSONObject> future = RequestFuture.newFuture();

        String jsonString = new Gson().toJson(highScore);
        JSONObject jsonRequest = new JSONObject(jsonString);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, url, jsonRequest, future, future);
        queue.add(request);
        JSONObject response = null;


        try {
            response = future.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        return response.getBoolean("success");
    }

    private enum RequestType {
        ALL("all", SERVER_URL + "all"), TOP("top/#", SERVER_URL + "top/"),
        NAME("name/*", SERVER_URL + "name/"), ADD("add", SERVER_URL + "add");

        private String cpPath;
        private String webURL;

        RequestType(String cpPath, String webURL) {
            this.cpPath = cpPath;
            this.webURL = webURL;
        }
    }
}
