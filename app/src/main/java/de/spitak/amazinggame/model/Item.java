package de.spitak.amazinggame.model;

import android.content.ContentValues;

import com.google.common.base.Strings;
import com.orm.SugarRecord;

/**
 * Created by rschlett on 10/28/16.
 */

public class Item extends SugarRecord implements Entity<Item> {

    private String name;
    private String description;
    private String image;
    private String smallImage;

    public Item() {
    }

    public Item(String name, String description, String image, String smallImage) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.smallImage = smallImage;
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

    public String getSmallImage() {
        return smallImage;
    }

    @Override
    public Item fromContentValueToEntity(ContentValues values) {
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
    }
}
