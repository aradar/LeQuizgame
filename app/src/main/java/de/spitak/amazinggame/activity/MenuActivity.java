package de.spitak.amazinggame.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

import de.spitak.amazinggame.R;
import de.spitak.amazinggame.model.Settings;
import io.realm.Realm;
import io.realm.RealmObject;

public class MenuActivity extends AppCompatActivity {

    private static Context appContext;
    private Realm realm;

    public static Context getAppContext() {
        return appContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        appContext = getApplicationContext();
        Realm.init(MenuActivity.getAppContext());
        realm = Realm.getDefaultInstance();

    }

    private void startActivity(Class type) {
        final Intent intent = new Intent(this, type);
        startActivity(intent);
    }

    public void onClickGameStart(View view)
    {
        Settings realmObject = realm.where(Settings.class).findFirst();

        if (realmObject != null &&
                realmObject.getName() != null &&
        !realmObject.getName().isEmpty())
            startActivity(GameActivity.class);
        else {
            Toast.makeText(this, R.string.insert_name, Toast.LENGTH_SHORT).show();
            startActivity(SettingsActivity.class);
        }

    }

    public void onClickShowHighscore(View view)
    {
        startActivity(HighscoreActivity.class);
    }

    public void onClickShowSettings(View view) {
        startActivity(SettingsActivity.class);
    }
}
