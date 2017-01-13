package de.spitak.amazinggame.viewmodel;

import android.databinding.BaseObservable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import de.spitak.amazinggame.model.Game;
import de.spitak.amazinggame.util.SwipeDetector;
import de.spitak.amazinggame.view.SwipeableCardView;

/**
 * Created by rschlett on 10/28/16.
 */

public class GameViewModel extends BaseObservable {
    private Game game;
    private List<OnBackButtonPressedListener> listeners;

    public GameViewModel(Game game) {
        this.game = game;
        listeners = new ArrayList<>();
    }

    public void addOnBackButtonPressedListener(OnBackButtonPressedListener l) {
        listeners.add(l);
    }

    private void onBackButtonPressed() {
        for (OnBackButtonPressedListener listener : listeners) {
            listener.onBackButtonPressed();
        }
    }

    public OptionViewModel getCurrentOptionViewModel() {
        return new OptionViewModel(game.getCurrentOption());
    }

    public SwipeableCardView.OnSwipeListener onSwipe() {
        return new SwipeableCardView.OnSwipeListener() {
            @Override
            public void onSwipe(SwipeEvent swipeEvent) {
                if (swipeEvent.getSwipeDirection().equals(SwipeDetector.SwipeDirection.LEFT)) {
                    game.takeLeftOption();
                } else if (swipeEvent.getSwipeDirection()
                        .equals(SwipeDetector.SwipeDirection.RIGHT)) {
                    game.takeRightOption();
                }
                notifyChange();
            }
        };
    }

    public void onBackClick(View view) {
        game.takeParentOption();
        onBackButtonPressed();
        notifyChange();
    }

    public interface OnBackButtonPressedListener {
        void onBackButtonPressed();
    }
}
