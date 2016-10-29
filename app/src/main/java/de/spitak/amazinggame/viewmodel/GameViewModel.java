package de.spitak.amazinggame.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.DataBindingUtil;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import de.spitak.amazinggame.R;
import de.spitak.amazinggame.Utils;
import de.spitak.amazinggame.databinding.OptionCardBinding;
import de.spitak.amazinggame.model.Game;
import de.spitak.amazinggame.model.Option;
import de.spitak.amazinggame.view.SwipeableCardView;

/**
 * Created by rschlett on 10/28/16.
 */

public class GameViewModel extends BaseObservable {
    private Game game;
    private Context context;
    private RelativeLayout layout;

    public GameViewModel(Context context, RelativeLayout layout, Game game) {
        this.context = context;
        this.game = game;
        this.layout = layout;
    }

    public Option getCurrentOption() {
        return game.getCurrentOption();
    }

    public SwipeableCardView.OnSwipeListener onSwipe() {
        return new SwipeableCardView.OnSwipeListener() {
            @Override
            public void OnSwipeEvent(SwipeEvent swipeEvent) {
                if (swipeEvent.getSwipeDirection().equals(SwipeableCardView.SwipeDirection.LEFT)) {
                    game.takeLeftOption();
                    createNewCard();
                } else if (swipeEvent.getSwipeDirection()
                        .equals(SwipeableCardView.SwipeDirection.RIGHT)) {
                    game.takeRightOption();
                    createNewCard();
                }
            }
        };
    }

    public void createNewCard() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        OptionCardBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.option_card,
                null,
                false);
        binding.setOptionViewModel(new OptionViewModel(getCurrentOption())); // should be automatically generated from the game?!
        SwipeableCardView card = (SwipeableCardView) binding.getRoot();
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        DisplayMetrics displayMetrics = Utils.getDisplayMetrics(context);
        final int DEFAULT_MARGIN = Utils.dpToPx(displayMetrics, 15);
        final int BOTTOM_MARGIN = Utils.dpToPx(displayMetrics, 120);
        layoutParams.setMargins(DEFAULT_MARGIN, DEFAULT_MARGIN, DEFAULT_MARGIN, BOTTOM_MARGIN);
        card.setLayoutParams(layoutParams);
        card.setOnSwipeListener(onSwipe());

        layout.removeAllViews();
        layout.addView(card);
    }

}
