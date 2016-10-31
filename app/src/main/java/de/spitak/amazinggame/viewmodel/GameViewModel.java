package de.spitak.amazinggame.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import de.spitak.amazinggame.R;
import de.spitak.amazinggame.util.Display;
import de.spitak.amazinggame.databinding.OptionCardBinding;
import de.spitak.amazinggame.model.Game;
import de.spitak.amazinggame.model.Option;
import de.spitak.amazinggame.view.SwipeableCardView;

/**
 * Created by rschlett on 10/28/16.
 */

public class GameViewModel extends BaseObservable {
    private Game game;


    public GameViewModel(Game game) {
        this.game = game;
    }

    public OptionViewModel getCurrentOption() {
        return new OptionViewModel(game.getCurrentOption());
    }

    public SwipeableCardView.OnSwipeListener onSwipe() {
        return new SwipeableCardView.OnSwipeListener() {
            @Override
            public void OnSwipeEvent(SwipeEvent swipeEvent) {
                if (swipeEvent.getSwipeDirection().equals(SwipeableCardView.SwipeDirection.LEFT)) {
                    game.takeLeftOption();
                    // trigger createNewCard
                } else if (swipeEvent.getSwipeDirection()
                        .equals(SwipeableCardView.SwipeDirection.RIGHT)) {
                    game.takeRightOption();
                    // trigger createNewCard
                }
            }
        };
    }

    public void onBackClick (View v) {
        if (!game.getCurrentOption().isRoot()) {
            game.takeParentOption();
            // trigger createNewCard
        }
    }
}
