package de.spitak.amazinggame.viewmodel;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.databinding.BaseObservable;
import android.net.Uri;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import de.spitak.amazinggame.activity.MenuActivity;
import de.spitak.amazinggame.model.Game;
import de.spitak.amazinggame.model.Settings;
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
                if (!game.isCompleted()) {
                    if (swipeEvent.getSwipeDirection()
                            .equals(SwipeDetector.SwipeDirection.LEFT)) {
                        game.takeLeftOption();
                        game.setMovesTaken(game.getMovesTaken() + 1);
                    } else if (swipeEvent.getSwipeDirection()
                            .equals(SwipeDetector.SwipeDirection.RIGHT)) {
                        game.takeRightOption();
                        game.setMovesTaken(game.getMovesTaken() + 1);
                    }
                    notifyChange();

                    if (game.getCurrentOption().isCompleted()) {

                        final ContentValues values = new ContentValues();
                        values.put("position", 0);
                        values.put("movesTaken", game.getMovesTaken());
                        values.put("name", Settings.getInstance().getName());

                        game.setCompleted(true);

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                MenuActivity.getAppContext().getContentResolver()
                                        .insert(Uri.parse(
                                                "content://de.spitak.amazinggame.HighScoreProvider/add"),
                                                values);
                            }
                        }).start();
                    }
                }
            }
        };
    }

    // TODO: 1/16/17 change this weird behavior
    public void onBackClick(View view) {
        if (!game.getCurrentOption().isRoot() && !game.getCurrentOption().isBackstepBlocked()) {
            if (view != null) {
                onBackButtonPressed();
            } else {
                game.takeParentOption();
                notifyChange();
            }
        }
    }

    public interface OnBackButtonPressedListener {
        void onBackButtonPressed();
    }
}
