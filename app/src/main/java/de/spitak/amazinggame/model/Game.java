package de.spitak.amazinggame.model;

import android.content.ContentValues;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by rschlett on 10/28/16.
 */

public class Game extends RealmObject implements Entity<Game> {

    private String name;
    private String description;
    private String image;
    @Ignore
    private Option currentOption;
    private RealmList<Option> options;

    public Game() {
        options = new RealmList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public RealmList<Option> getOptions() {
        return options;
    }

    public void setOptions(RealmList<Option> options) {
        this.options = options;
    }

    public Option getCurrentOption() {
        if (currentOption == null)
            currentOption = options.first();

        return currentOption;
    }

    public void setCurrentOption(Option currentOption) {
        this.currentOption = currentOption;
    }

    public void takeLeftOption() {
        if (currentOption.getLeft() != null)
            currentOption = currentOption.getLeft();
    }

    public void takeRightOption() {
        if (currentOption.getRight() != null)
            currentOption = currentOption.getRight();
    }

    @Override
    public Game fromContentValueToEntity(ContentValues values) throws IllegalArgumentException {
/*
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
*/
        return null;
    }

    public void takeParentOption() {
        if (currentOption.getParent() != null)
            currentOption = currentOption.getParent();
    }
}
