package de.spitak.amazinggame.model;

import android.content.ContentValues;

import com.google.common.base.Strings;
import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by rschlett on 10/28/16.
 */

public class Option extends SugarRecord implements Entity<Option> {

    private Game game;
    private String title;
    private String description;
    private String hint;
    private String image;
    //private Option parent;
    //private Option left;
    //private Option right;
    private boolean backstepBlocked;
    private boolean completed;
    @Unique
    private long position;

    public Option() {
    }

    public Option(Game game, String title, String description, String hint, String image,
                  boolean backstepBlocked, boolean completed, long position) {
        this.game = game;
        this.title = title;
        this.description = description;
        this.hint = hint;
        this.image = image;
        this.backstepBlocked = backstepBlocked;
        this.completed = completed;
        this.position = position;
    }

    @Override
    public Option fromContentValueToEntity(ContentValues values) throws IllegalArgumentException {
        boolean isNotNull = true;
        //isNotNull = isNotNull && values.getAsInteger("game") != -1;
        isNotNull = isNotNull && Game.findById(Game.class, values.getAsInteger("game")) != null;
        isNotNull = isNotNull && !Strings.isNullOrEmpty(values.getAsString("title"));
        isNotNull = isNotNull && !Strings.isNullOrEmpty(values.getAsString("description"));
        isNotNull = isNotNull && !Strings.isNullOrEmpty(values.getAsString("hint"));
        isNotNull = isNotNull && !Strings.isNullOrEmpty(values.getAsString("image"));
        isNotNull = isNotNull && values.getAsInteger("position") != -1;

        if (isNotNull)
            return new Option(Game.findById(Game.class, values.getAsInteger("game")),
                    values.getAsString("title"),
                    values.getAsString("description"),
                    values.getAsString("hint"),
                    values.getAsString("image"),
                    values.getAsBoolean("backstepBlocked"),
                    values.getAsBoolean("completed"),
                    values.getAsLong("position"));
        else
            throw new IllegalArgumentException("The column names have to be complete.");
    }
}
