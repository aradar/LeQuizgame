package de.spitak.amazinggame.model;

import android.content.ContentValues;

import com.google.common.base.Strings;
import com.orm.SugarRecord;

/**
 * Created by rschlett on 10/28/16.
 */

public class Game extends SugarRecord implements Entity<Game> {

    private Option currentOption;
    private String name;
    private String description;
    private String image;

    public Game() {
    }

    public Game(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }


    @Override
    public Game fromContentValueToEntity(ContentValues values) throws IllegalArgumentException {
        boolean isNotNull = true;
        isNotNull = isNotNull && !Strings.isNullOrEmpty(values.getAsString("name"));
        isNotNull = isNotNull && !Strings.isNullOrEmpty(values.getAsString("description"));
        isNotNull = isNotNull && !Strings.isNullOrEmpty(values.getAsString("image"));

        if (isNotNull)
            return new Game(values.getAsString("name"),
                    values.getAsString("description"),
                    values.getAsString("image"));
        else
            throw new IllegalArgumentException("The column names have to be complete.");
    }
}
