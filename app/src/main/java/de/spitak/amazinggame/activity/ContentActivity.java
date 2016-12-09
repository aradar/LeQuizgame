package de.spitak.amazinggame.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import de.spitak.amazinggame.R;
import de.spitak.amazinggame.db.GameDataSource;
import de.spitak.amazinggame.model.Game;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Game game = new Game("Beste Game",
                "Du musst dir in diesem Vorlesungsraum schwachsinn anhören!",
                "");
        GameDataSource source = new GameDataSource(this);
        source.open();
        source.createData(game);
        List<Game> values = source.getAllData();

        for (Game game1 :
                values) {
            Toast.makeText(this, Integer.toString(game1.getId()), Toast.LENGTH_SHORT).show();
        }
    }
}
