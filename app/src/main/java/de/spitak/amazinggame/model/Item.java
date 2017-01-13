package de.spitak.amazinggame.model;

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
}
