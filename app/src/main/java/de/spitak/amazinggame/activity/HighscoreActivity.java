package de.spitak.amazinggame.activity;

import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import de.spitak.amazinggame.R;

public class HighscoreActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int LOADER_ID = 0x01;
    private CursorAdapter adapter;
    private ProgressBar progress;
    private static final String[] columns = new String[] { "position", "movesTaken", "name" };



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_highscore);
        getSupportLoaderManager().initLoader(LOADER_ID,null,this);

        adapter = new SimpleCursorAdapter(this, R.layout.list_item, null,
                columns,
                new int[]{R.id.position_text,R.id.movestaken_text,R.id.name_text},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        progress = (ProgressBar)findViewById(R.id.load_progress);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                Uri.parse("content://de.spitak.amazinggame.HighScoreProvider/all"),null,null,
                null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
        progress.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
