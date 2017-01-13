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
                card.bottomSwipeOut(new Runnable() {
                    @Override
                    public void run() {
                        gameViewModel.onBackClick(null);
                        card.topSwipeIn(null);
                    }
                });
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

        Option head = realm.createObject(Option.class);
        head.setTitle("kopf");
        head.setDescription("ich bin ein kopf");
        Option left = realm.createObject(Option.class);
        left.setTitle("links");
        left.setDescription("ich bin ein fuss");
        Option right = realm.createObject(Option.class);
        right.setTitle("rechts");
        right.setDescription("ich bin ein fuss");
        Option leftLeft = realm.createObject(Option.class);
        leftLeft.setTitle("linkslinks");
        leftLeft.setDescription("ich bin ein fuss");
        Option leftRight = realm.createObject(Option.class);
        leftRight.setTitle("linksrechts");
        leftRight.setDescription("ich bin ein fuss");
        Option rightLeft = realm.createObject(Option.class);
        rightLeft.setTitle("rechtslinks");
        rightLeft.setDescription("ich bin ein fuss");
        Option rightRight = realm.createObject(Option.class);
        rightRight.setTitle("rechtsrechts");
        rightRight.setDescription("ich bin ein fuss");
        Option leftLeftEnd = realm.createObject(Option.class);
        leftLeftEnd.setTitle("linkslinks ende");
        leftLeftEnd.setBackstepBlocked(true);
        leftLeftEnd.setDescription("DAS ENDE");
        Option leftRightEnd = realm.createObject(Option.class);
        leftRightEnd.setTitle("linksrechts ende");
        leftRightEnd.setBackstepBlocked(true);
        leftRightEnd.setDescription("DAS ENDE");
        Option rightLeftEnd = realm.createObject(Option.class);
        rightLeftEnd.setTitle("rechtslinks ende");
        rightLeftEnd.setBackstepBlocked(true);
        rightLeftEnd.setDescription("DAS ENDE");
        Option rightRightEnd = realm.createObject(Option.class);
        rightRightEnd.setTitle("rechtsrechts ende");
        rightRightEnd.setBackstepBlocked(true);
        rightRightEnd.setDescription("DAS ENDE");

        head.setLeft(left);
        head.setRight(right);

        left.setParent(head);
        left.setLeft(leftLeft);
        leftLeft.setParent(left);
        leftLeft.setLeft(leftLeftEnd);
        leftLeft.setRight(leftLeftEnd);
        leftLeftEnd.setParent(leftLeft);
        left.setRight(leftRight);
        leftRight.setParent(left);
        leftRight.setLeft(leftRightEnd);
        leftRight.setRight(leftRightEnd);
        leftRightEnd.setParent(leftRight);

        right.setParent(head);
        right.setLeft(rightLeft);
        rightLeft.setParent(right);
        rightLeft.setLeft(leftRightEnd);
        rightLeft.setRight(leftRightEnd);
        leftRightEnd.setParent(rightLeft);
        rightRight.setParent(rightLeft);
        right.setRight(rightRight);
        rightRight.setParent(right);
        rightRight.setLeft(rightRightEnd);
        rightRight.setRight(rightRightEnd);

        game.getOptions()
                .addAll(Arrays.asList(head,
                        left,
                        right,
                        leftLeft,
                        leftRight,
                        rightLeft,
                        rightRight));
        game.setCurrentOption(head);

        realm.commitTransaction();
        return realm.where(Game.class).findFirst();
    }
}

