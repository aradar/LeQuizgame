package de.spitak.amazinggame.model;

/**
 * Created by rschlett on 10/28/16.
 */

public class Game {
    private Option currentOption;
    private Inventory inventory;

    public Game() {
        createTestOption();
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
