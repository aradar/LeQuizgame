package de.spitak.amazinggame.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import de.spitak.amazinggame.R;
import de.spitak.amazinggame.db.datasource.GameDataSource;
import de.spitak.amazinggame.db.datasource.OptionDataSource;
import de.spitak.amazinggame.model.Game;
import de.spitak.amazinggame.model.Option;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Game game = new Game("Beste Game",
                "Du musst dir in diesem Vorlesungsraum schwachsinn anhören!",
                "");
        Option option = new Option("Du befindest dich in einem verlassenen Raum",
                "Ich kann kaum etwas sehen.","","",-1,1,2,true,false);
        GameDataSource gameSource = new GameDataSource(this);
        OptionDataSource optionSource = new OptionDataSource(this);
        gameSource.open();
        gameSource.createData(game);
        optionSource.open();
        optionSource.createData(option);
        List<Game> values = gameSource.getAllData();

        for (Game game1 :
                values) {
            Toast.makeText(this, Integer.toString(game1.getId()), Toast.LENGTH_SHORT).show();
        }
    }
}
