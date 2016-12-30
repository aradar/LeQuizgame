package de.spitak.amazinggame.model;

import de.spitak.amazinggame.db.base.Entity;

/**
 * Created by rschlett on 10/28/16.
 */

public class Item implements Entity {

    private long id;
    private String name;
    private String description;
    private String image;
    private String smallImage;

    public Item(String name, String description, String image, String smallImage) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.smallImage = smallImage;
    }

    public Item(int id, String name, String description, String image, String smallImage) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.smallImage = smallImage;
    }

    @Override
    public long getId() {
        return id;
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
}
