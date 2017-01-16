package de.spitak.amazinggame.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.Ignore;

/**
 * Created by rschlett on 10/28/16.
 */

public class Game extends RealmObject {

    private String name;
    private String description;
    private String image;
    private RealmList<Option> options;
    private RealmList<Item> inventory;
    private int movesTaken;

    @Ignore
    private Option currentOption;

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

    public int getMovesTaken() {
        return movesTaken;
    }

    public void setMovesTaken(int movesTaken) {
        this.movesTaken = movesTaken;
    }

    public Option getCurrentOption() {
        if (currentOption == null) {
            currentOption = options.first();
        }

        return currentOption;
    }

    public void setCurrentOption(Option currentOption) {
        this.currentOption = currentOption;
    }

    public void takeLeftOption() {
        takeOption(currentOption.getLeft());
    }

    public void takeRightOption() {
        takeOption(currentOption.getRight());
    }

    private void takeOption(Option option) {
        if (option != null && requirementsMet(option.getRequirements())) {
            currentOption = currentOption.getRight();
        }
    }

    private boolean requirementsMet(RealmList<Item> requirements) {
        if (requirements != null) {
            for (Item requirement : requirements) {
                if (!inventory.contains(requirement)) {
                    return false;
                }
            }
        }

        return true;
    }

    public void takeParentOption() {
        if (currentOption.getParent() != null && !currentOption.isBackstepBlocked())
            currentOption = currentOption.getParent();
    }

    public RealmList<Item> getInventory() {
        return inventory;
    }

    public void setInventory(RealmList<Item> inventory) {
        this.inventory = inventory;
    }
}
