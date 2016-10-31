package de.spitak.amazinggame.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import de.spitak.amazinggame.R;
import de.spitak.amazinggame.databinding.ActivityGameBinding;
import de.spitak.amazinggame.databinding.OptionCardBinding;
import de.spitak.amazinggame.model.Game;
import de.spitak.amazinggame.view.SwipeableCardView;
import de.spitak.amazinggame.viewmodel.GameViewModel;

public class GameActivity extends AppCompatActivity {

    private RelativeLayout layout;
    private GameViewModel gameViewModel;
    private ActivityGameBinding activityGameBinding;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityGameBinding = DataBindingUtil.setContentView(this, R.layout.activity_game);

        layout = (RelativeLayout) findViewById(R.id.activity_game);
        gameViewModel = new GameViewModel(new Game());
        activityGameBinding.setGame(gameViewModel);

        SwipeableCardView card = getSwipeableCardView(layout);
        layout.addView(card);
    }

    @NonNull
    private SwipeableCardView getSwipeableCardView(final RelativeLayout layout) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // create binding with parent layout
        final OptionCardBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.option_card,
                layout, false);

        binding.setOption(gameViewModel.getCurrentOption());

        SwipeableCardView card = ((SwipeableCardView)binding.getRoot());
        card.setOnSwipeListener(gameViewModel.onSwipe());

        return card;
    }

}

