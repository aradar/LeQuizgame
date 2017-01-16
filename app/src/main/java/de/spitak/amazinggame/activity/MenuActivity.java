package de.spitak.amazinggame.activity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import de.spitak.amazinggame.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Cursor c = getContentResolver().query(Uri
                        .parse("content://de.spitak.amazinggame.HighScoreProvider/top/5")
                        ,null,null,null,null);
            }
        }).start();

    }

    private void startActivity(Class type) {
        final Intent intent = new Intent(this, type);
        startActivity(intent);
    }

    public void onClickGameStart(View view)
    {
        startActivity(GameActivity.class);
    }
}
