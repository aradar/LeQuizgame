package de.spitak.amazinggame;

/**
 * Created by rschlett on 10/24/16.
 */
public interface OnSwipeListener {
    void OnSwipeEvent(SwipeEvent swipeEvent);

    class SwipeEvent {
        private SwipeableCardView.SwipeDirection swipeDirection;

        public SwipeEvent(SwipeableCardView.SwipeDirection swipeDirection) {
            this.swipeDirection = swipeDirection;
        }

        public SwipeableCardView.SwipeDirection getSwipeDirection() {
            return swipeDirection;
        }
    }
}
