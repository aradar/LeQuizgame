package de.spitak.amazinggame.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import de.spitak.amazinggame.R;
import de.spitak.amazinggame.util.Display;
import de.spitak.amazinggame.databinding.OptionCardBinding;
import de.spitak.amazinggame.model.Game;
import de.spitak.amazinggame.view.SwipeableCardView;
import de.spitak.amazinggame.viewmodel.GameViewModel;
import de.spitak.amazinggame.viewmodel.OptionViewModel;

public class GameActivity extends AppCompatActivity {

    private RelativeLayout layout;
    private GameViewModel gameViewModel;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        layout = (RelativeLayout) findViewById(R.id.activity_swipe_test);
        gameViewModel = new GameViewModel(this, layout, generateGame());
        //activityBinding.setGameViewModel(var);

        //gameViewModel.createNewCard();
        //option = Option.getTestOption();
        SwipeableCardView card = getSwipeableCardView(layout);
        layout.addView(card);
    }

    @NonNull
    private Game generateGame() {
        Game game = new Game();
        game.createTestOption();
        return game;
    }

    @NonNull
    private SwipeableCardView getSwipeableCardView(final RelativeLayout layout) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        OptionCardBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.option_card,
                null,
                false);
        binding.setOptionViewModel(new OptionViewModel(gameViewModel.getCurrentOption())); // should be automatically generated from the game?!
        SwipeableCardView card = (SwipeableCardView) binding.getRoot();
        //SwipeableCardView card = (SwipeableCardView) inflater.inflate(R.layout.option_card, null);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        DisplayMetrics displayMetrics = Display.getDisplayMetrics(this);
        final int DEFAULT_MARGIN = Display.dpToPx(displayMetrics, 15);
        final int BOTTOM_MARGIN = Display.dpToPx(displayMetrics, 120);
        layoutParams.setMargins(DEFAULT_MARGIN, DEFAULT_MARGIN, DEFAULT_MARGIN, BOTTOM_MARGIN);
        card.setLayoutParams(layoutParams);
        card.setOnSwipeListener(gameViewModel.onSwipe());

        /*
        ((TextView) card.findViewById(R.id.room_card_title)).setText(option.getTitle());
        ((ImageView) card.findViewById(R.id.room_card_image)).setImageDrawable(option.getImage());
        ((TextView) card.findViewById(R.id.room_card_description)).setText(option.getDescription());
        if (option.left != null)
            ((TextView) card.findViewById(R.id.room_card_lefthint)).setText(option.getLeft()
                    .getHint());
        if (option.getRight() != null)
            ((TextView) card.findViewById(R.id.room_card_righthint)).setText(option.getRight()
                    .getHint());

        card.setOnSwipeListener(new SwipeableCardView.OnSwipeListener() {
            @Override
            public void OnSwipeEvent(SwipeEvent swipeEvent) {
                if (swipeEvent.getSwipeDirection().equals(SwipeableCardView.SwipeDirection.LEFT)) {
                    if (option.getLeft() != null)
                        option = option.getLeft();
                } else {
                    if (option.getRight() != null)
                        option = option.getRight();
                }

                SwipeableCardView newCard = getSwipeableCardView(layout);
                newCard.setAlpha(0);
                layout.addView(newCard);
                newCard.animate().alpha(1).start();
            }
        });*/

        return card;

        /*SwipeableCardView card = new SwipeableCardView(layout.getContext());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);
        DisplayMetrics displayMetrics = Display.getDisplayMetrics(this);
        final int DEFAULT_MARGIN = Display.dpToPx(displayMetrics, 15);
        final int BOTTOM_MARGIN = Display.dpToPx(displayMetrics, 120);
        layoutParams.setMargins(DEFAULT_MARGIN, DEFAULT_MARGIN, DEFAULT_MARGIN, BOTTOM_MARGIN);
        card.setLayoutParams(layoutParams);
        card.setOnSwipeListener(new SwipeableCardView.OnSwipeListener() {
            @Override
            public void OnSwipeEvent(SwipeEvent swipeEvent) {
                // TODO: 10/24/16 removeAllViews() seems to cancel all animations and just addView() does not show the new view
                //layout.removeAllViews();
                SwipeableCardView card = getSwipeableCardView(layout);
                card.setAlpha(0);
                layout.addView(card);
                card.animate().alpha(1).start();
            }
        });
        return card;*/
    }

    public void onClickBackOption(View view) {
       /* if (option.getParent() != null) {
            option = option.getParent();
            layout.addView(getSwipeableCardView(layout));
        }*/

    }
}

