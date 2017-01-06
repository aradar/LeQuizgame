package de.spitak.amazinggame.activity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.orm.SugarContext;

import java.util.Arrays;
import java.util.List;

import de.spitak.amazinggame.R;
import de.spitak.amazinggame.model.Game;
import de.spitak.amazinggame.model.Option;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        SugarContext.init(this);

        Game game = new Game("hallo", "ich bin eine beschreibung", "");
        game.save();


        Option.deleteAll(Option.class);
        Option o1 = new Option(game, "kopf", "ich bin ein kopf", "", "", false, false, 1);
        Option o2 = new Option(game, "links", "ich bin ein fuss", "", "", false, false, 2);
        Option o3 = new Option(game, "rechts", "ich bin ein fuss", "", "", false, false, 3);
        Option o4 = new Option(game, "linkslinks", "ich bin ein fuss", "", "", false, false, 4);
        Option o5 = new Option(game, "linksrechts", "ich bin ein fuss", "", "", false, false, 5);
        Option o6 = new Option(game, "rechtslinks", "ich bin ein fuss", "", "", false, false, 6);
        Option o7 = new Option(game, "rechtsrechts", "ich bin ein fuss", "", "", false, false, 7);

        List<Option> optionList = Arrays.asList(new Option[]{o1, o2, o3, o4, o5, o6, o7});
        for (Option option :
                optionList) {
            option.save();
        }

        optionList = Option.find(Option.class,
                "game = ?",
                new String[]{Long.toString(game.getId())});

        Cursor cursor = getContentResolver()
                .query(
                        Uri.parse("content://de.spitak.amazinggame.SomethingSomethingProvider/games"),
                        null,
                        "id=1",
                        null,
                        null);
    }
}
