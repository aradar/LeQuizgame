package de.spitak.amazinggame.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.Arrays;
import java.util.UUID;

import de.spitak.amazinggame.R;
import de.spitak.amazinggame.model.Game;
import de.spitak.amazinggame.model.Option;
import io.realm.Realm;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        Game game = realm.createObject(Game.class, UUID.randomUUID().toString());
        game.setName("hallo");
        game.setDescription("ich bin eine beschreibung");

        Option o1 = realm.createObject(Option.class, UUID.randomUUID().toString());
        o1.setTitle("kopf");
        o1.setDescription("ich bin ein kopf");
        Option o2 = realm.createObject(Option.class, UUID.randomUUID().toString());
        o2.setTitle("links");
        o2.setDescription("ich bin ein fuss");
        Option o3 = realm.createObject(Option.class, UUID.randomUUID().toString());
        o3.setTitle("rechts");
        o3.setDescription("ich bin ein fuss");
        Option o4 = realm.createObject(Option.class, UUID.randomUUID().toString());
        o4.setTitle("linkslinks");
        o4.setDescription("ich bin ein fuss");
        Option o5 = realm.createObject(Option.class, UUID.randomUUID().toString());
        o5.setTitle("linksrechts");
        o5.setDescription("ich bin ein fuss");
        Option o6 = realm.createObject(Option.class, UUID.randomUUID().toString());
        o6.setTitle("rechtslinks");
        o6.setDescription("ich bin ein fuss");
        Option o7 = realm.createObject(Option.class, UUID.randomUUID().toString());
        o7.setTitle("rechtsrechts");
        o7.setDescription("ich bin ein fuss");

        o1.setLeft(o2);
        o1.setRight(o3);
        o2.setParent(o1);
        o2.setLeft(o4);
        o4.setParent(o2);
        o2.setRight(o5);
        o5.setParent(o2);
        o3.setParent(o1);
        o3.setLeft(o6);
        o6.setParent(o3);
        o3.setRight(o7);
        o7.setParent(o3);


        game.getOptions().addAll(Arrays.asList(o1, o2, o3, o4, o5, o6, o7));


        realm.commitTransaction();
        Game thebestgame = realm.where(Game.class).findFirst();
        Option option = thebestgame.getOptions().get(0);
        while (option != null) {
            Toast.makeText(this, option.getTitle(), Toast.LENGTH_SHORT).show();
            option = option.getRight();
        }
    }
}
