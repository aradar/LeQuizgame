package de.spitak.amazinggame.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.spitak.amazinggame.R;
import de.spitak.amazinggame.databinding.ActivityGameBinding;
import de.spitak.amazinggame.model.Game;
import de.spitak.amazinggame.view.SwipeableCardView;
import de.spitak.amazinggame.viewmodel.GameViewModel;

public class GameActivity extends AppCompatActivity {

    private GameViewModel gameViewModel;
    private ActivityGameBinding activityGameBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_game);

        gameViewModel = new GameViewModel(new Game(0, "Hallo", "Welt", "Image"));
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
}

