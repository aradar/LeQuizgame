package de.spitak.amazinggame.model;

import android.content.ContentValues;

/**
 * Created by dephiloper on 11.01.17.
 */

public class HighScore {
    private int position;
    private String name;
    private int movesTaken;

    public int getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public int getMovesTaken() {
        return movesTaken;
    }

    public static HighScore toHighScore(ContentValues values) {
        if (values == null)
            return null;

        HighScore highScore = new HighScore();

        if (values.getAsInteger("position") != null)
            highScore.position = values.getAsInteger("position");

        if (values.getAsString("name") != null)
            highScore.name = values.getAsString("name");

        if (values.getAsInteger("movesTaken") != null)
            highScore.movesTaken = values.getAsInteger("movesTaken");

        return highScore;
    }
}
