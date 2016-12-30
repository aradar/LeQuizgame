package de.spitak.amazinggame.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import de.spitak.amazinggame.R;
import de.spitak.amazinggame.db.datasource.GameDataSource;
import de.spitak.amazinggame.model.Game;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
//        GameDataSource gameSource = new GameDataSource(this);
//        OptionDataSource optionSource = new OptionDataSource(this);
//        ItemDataSource itemSource = new ItemDataSource(this);
//        LootDataSource lootSource = new LootDataSource(this);
//        RequirementDataSource requirementSource = new RequirementDataSource(this);
//
//        Game game = new Game("Beste Game",
//                "Du musst dir in diesem Vorlesungsraum schwachsinn anhören!",
//                "");
//        Option option = new Option(1,"Du befindest dich in einem verlassenen Raum",
//                "Ich kann kaum etwas sehen.","","",-1,1,2,true,false);
//        gameSource.open();
//        gameSource.delete();
//        gameSource.insert(game);
//        optionSource.open();
//        optionSource.insert(option);
//        List<Game> values = gameSource.query();
//
//        for (Game game1 : values) {
//            Toast.makeText(this, Long.toString(game1.getId()), Toast.LENGTH_SHORT).show();
//        }

        ContentResolver contentResolver = getContentResolver();
        Uri gameUri = Uri.parse("content://de.spitak.amazinggame.SomethingSomethingProvider/games");

        System.out.println(gameUri == null);

        Game game = new Game("Beste Game",
                "Du musst dir in diesem Vorlesungsraum schwachsinn anhören!",
                "");
        GameDataSource gameDataSource = new GameDataSource(this);
        ContentValues values = gameDataSource.entityToContentValues(game);
        contentResolver.insert(gameUri, values);

        Cursor cursor = contentResolver.query(gameUri, null, null, null, null);
        cursor.moveToFirst();
        do {
            Game game2 = gameDataSource.cursorToEntity(cursor);
            Toast.makeText(this, Long.toString(game2.getId()), Toast.LENGTH_SHORT).show();
        } while (cursor.moveToNext());
    }
}
