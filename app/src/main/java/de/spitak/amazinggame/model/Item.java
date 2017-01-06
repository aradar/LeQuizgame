package de.spitak.amazinggame.model;

import android.content.ContentValues;

import io.realm.RealmObject;

/**
 * Created by rschlett on 10/28/16.
 */

public class Item extends RealmObject implements Entity<Item> {

    private String name;
    private String description;
    private String image;
    private String smallImage;

    public Item() {
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

    public String getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(String smallImage) {
        this.smallImage = smallImage;
    }

    @Override
    public Item fromContentValueToEntity(ContentValues values) {
/*
        boolean isNotNull = true;
        isNotNull = isNotNull && Strings.isNullOrEmpty(values.getAsString("name"));
        isNotNull = isNotNull && Strings.isNullOrEmpty(values.getAsString("description"));
        isNotNull = isNotNull && Strings.isNullOrEmpty(values.getAsString("image"));
        isNotNull = isNotNull && Strings.isNullOrEmpty(values.getAsString("smallImage"));

        if (isNotNull)
            return new Item(values.getAsString("name"), values.getAsString("description"),
                    values.getAsString("image"), values.getAsString("smallImage"));
        else
            throw new IllegalArgumentException("The column names have to be complete.");
*/
        return null;
    }
}
