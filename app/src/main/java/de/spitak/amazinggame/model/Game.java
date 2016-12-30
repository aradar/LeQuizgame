package de.spitak.amazinggame.model;

import de.spitak.amazinggame.db.base.Entity;

/**
 * Created by rschlett on 10/28/16.
 */

public class Game implements Entity {
    private Option currentOption;
    private long id;
    private String name;
    private String description;
    private String image;

    public Game(String name, String description, String image) {
        this.name = name;
        this.description = description;
        this.image = image;
        createTestOption();
    }

    public Game(int id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
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

    public void takeLeftOption() {
        if (currentOption.getLeft() != null) {
            currentOption = currentOption.getLeft();
        }
    }

    public void takeRightOption() {
        if (currentOption.getRight() != null) {
            currentOption = currentOption.getRight();
        }
    }

    public void takeParentOption() { currentOption = currentOption.getParent(); }

    public Option getCurrentOption() {
        return currentOption;
    }

    private void createTestOption() {
        currentOption = new Option("Test", "Test 2000", "");

        currentOption.setLeft(new Option("Kuh",
                "Macht muh",
                "finde die Kuh",
                null,
                currentOption,
                null,
                null));
        currentOption.setRight(new Option("Hund",
                "oihrwqwoih",
                "was macht der Hund",
                null,
                currentOption,
                null,
                null));
    }
}
