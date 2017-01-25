package de.spitak.amazinggame.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;

import de.spitak.amazinggame.DummyGameGenerator;
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

        gameViewModel = new GameViewModel(DummyGameGenerator.createCustomGame());
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
                        //card.resetToCenter(null);
                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

