package de.spitak.amazinggame.model;

/**
 * Created by rschlett on 10/28/16.
 */

public class Game {
    private Option currentOption;
    private Inventory inventory;

    public void takeLeftOption() {
        currentOption = currentOption.getLeft();
    }

    public void takeRightOption() {
        currentOption = currentOption.getRight();
    }

    public Option getCurrentOption() {
        return currentOption;
    }

    public void createTestOption() {
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
