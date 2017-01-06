package de.spitak.amazinggame.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;

import de.spitak.amazinggame.R;
import de.spitak.amazinggame.databinding.ActivityGameBinding;
import de.spitak.amazinggame.model.Game;
import de.spitak.amazinggame.model.Option;
import de.spitak.amazinggame.view.SwipeableCardView;
import de.spitak.amazinggame.viewmodel.GameViewModel;
import io.realm.Realm;

public class GameActivity extends AppCompatActivity {

    private GameViewModel gameViewModel;
    private ActivityGameBinding activityGameBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_game);

        Game g = getGame();

        gameViewModel = new GameViewModel(g);
        activityGameBinding.setGame(gameViewModel);

        final SwipeableCardView card = (SwipeableCardView) findViewById(R.id.activity_game_card);
        card.setOnSwipeListener(gameViewModel.onSwipe());

        gameViewModel.addOnBackButtonPressedListener(new GameViewModel.OnBackButtonPressedListener() {
            @Override
            public void onBackButtonPressed() {
                //card.startBackAnimation();
            }
        });
    }

    private Game getGame() {
        Realm.init(this);
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        Game game = realm.createObject(Game.class);
        game.setName("hallo");
        game.setDescription("ich bin eine beschreibung");

        Option o1 = realm.createObject(Option.class);
        o1.setTitle("kopf");
        o1.setDescription("ich bin ein kopf");
        Option o2 = realm.createObject(Option.class);
        o2.setTitle("links");
        o2.setDescription("ich bin ein fuss");
        Option o3 = realm.createObject(Option.class);
        o3.setTitle("rechts");
        o3.setDescription("ich bin ein fuss");
        Option o4 = realm.createObject(Option.class);
        o4.setTitle("linkslinks");
        o4.setDescription("ich bin ein fuss");
        Option o5 = realm.createObject(Option.class);
        o5.setTitle("linksrechts");
        o5.setDescription("ich bin ein fuss");
        Option o6 = realm.createObject(Option.class);
        o6.setTitle("rechtslinks");
        o6.setDescription("ich bin ein fuss");
        Option o7 = realm.createObject(Option.class);
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
        game.setCurrentOption(o1);

        realm.commitTransaction();
        return realm.where(Game.class).findFirst();
    }
}

