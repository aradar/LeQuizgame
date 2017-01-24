package de.spitak.amazinggame.model;

import java.util.Arrays;

import de.spitak.amazinggame.activity.MenuActivity;
import io.realm.Realm;
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

    @Ignore
    private boolean completed;

    // TODO: 1/16/17 can be changed later
    @Ignore
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public static Game createCustomGame() {
        Realm.init(MenuActivity.getAppContext());
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        Game game = realm.createObject(Game.class);
        game.setName("Cheers!");
        game.setDescription("Dein Schädel brummt wie Sau. " +
                "Du befindest dich in ... ja wo befindest" +
                "du dich eigentlich? Versuche herauszufinden, " +
                "wo du bist und was passiert ist. In deinem" +
                "Besitz befindet sich eine Ananas.");
        /*Item
        game.addItem()
*/
        Option head = realm.createObject(Option.class);
        head.setTitle("kopf");
        head.setDescription("ich bin ein kopf");
        Option left = realm.createObject(Option.class);
        left.setTitle("links");
        left.setDescription("ich bin ein fuss");
        left.setHint("hihi ich bin ein linker hint");
        Option right = realm.createObject(Option.class);
        right.setTitle("rechts");
        right.setDescription("ich bin ein fuss");
        right.setHint("hihi ich bin ein rechter hint");
        Option leftLeft = realm.createObject(Option.class);
        leftLeft.setTitle("linkslinks");
        leftLeft.setDescription("ich bin ein fuss");
        Option leftRight = realm.createObject(Option.class);
        leftRight.setTitle("linksrechts");
        leftRight.setDescription("ich bin ein fuss");
        Option rightLeft = realm.createObject(Option.class);
        rightLeft.setTitle("rechtslinks");
        rightLeft.setDescription("ich bin ein fuss");
        Option rightRight = realm.createObject(Option.class);
        rightRight.setTitle("rechtsrechts");
        rightRight.setDescription("ich bin ein fuss");
        Option leftLeftEnd = realm.createObject(Option.class);
        leftLeftEnd.setTitle("linkslinks ende");
        leftLeftEnd.setBackstepBlocked(true);
        leftLeftEnd.setDescription("DAS ENDE");
        leftLeftEnd.setCompleted(true);
        Option leftRightEnd = realm.createObject(Option.class);
        leftRightEnd.setTitle("linksrechts ende");
        leftRightEnd.setBackstepBlocked(true);
        leftRightEnd.setDescription("DAS ENDE");
        leftRightEnd.setCompleted(true);
        Option rightLeftEnd = realm.createObject(Option.class);
        rightLeftEnd.setTitle("rechtslinks ende");
        rightLeftEnd.setBackstepBlocked(true);
        rightLeftEnd.setDescription("DAS ENDE");
        rightLeftEnd.setCompleted(true);
        Option rightRightEnd = realm.createObject(Option.class);
        rightRightEnd.setTitle("rechtsrechts ende");
        rightRightEnd.setBackstepBlocked(true);
        rightRightEnd.setCompleted(true);
        rightRightEnd.setDescription("DAS ENDE");

        head.setLeft(left);
        head.setRight(right);

        left.setParent(head);
        left.setLeft(leftLeft);
        leftLeft.setParent(left);
        leftLeft.setLeft(leftLeftEnd);
        leftLeft.setRight(leftLeftEnd);
        leftLeftEnd.setParent(leftLeft);
        left.setRight(leftRight);
        leftRight.setParent(left);
        leftRight.setLeft(leftRightEnd);
        leftRight.setRight(leftRightEnd);
        leftRightEnd.setParent(leftRight);

        right.setParent(head);
        right.setLeft(rightLeft);
        rightLeft.setParent(right);
        rightLeft.setLeft(leftRightEnd);
        rightLeft.setRight(leftRightEnd);
        leftRightEnd.setParent(rightLeft);
        rightRight.setParent(rightLeft);
        right.setRight(rightRight);
        rightRight.setParent(right);
        rightRight.setLeft(rightRightEnd);
        rightRight.setRight(rightRightEnd);

        game.getOptions()
                .addAll(Arrays.asList(head,
                        left,
                        right,
                        leftLeft,
                        leftLeftEnd,
                        leftRight,
                        leftRightEnd,
                        rightLeft,
                        rightLeftEnd,
                        rightRight,
                        rightRightEnd));
        game.setCurrentOption(head);

        realm.commitTransaction();
        //return realm.where(Game.class).findFirst();
        return game;
    }
}
