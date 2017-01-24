package de.spitak.amazinggame.model;

import android.view.Menu;
import android.widget.Toast;

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

    private void setCurrentOption(Option currentOption) {
        this.currentOption = currentOption;
    }

    public void takeLeftOption() {
        takeOption(currentOption.getLeft());
    }

    public void takeRightOption() {
        takeOption(currentOption.getRight());
    }

    private void takeOption(Option option) {
        if (option != null && requirementsMet(currentOption.getRight().getRequirements())) {
            currentOption = option;
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
            takeOption(currentOption.getParent());
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
        game.setDescription("A simple game about drunk people and where to find them.");
        /*Item
        game.addItem()
*/
        Option start = realm.createObject(Option.class);
        start.setTitle("Cheers!");
        start.setDescription("Dein Schädel brummt wie Sau. Du befindest dich in… ja wo " +
                "befindest du dich eigentlich? Das sieht aus wie das Innere eines Schrankes. " +
                "Versuche herauszufinden, was passiert ist. Was ist dein nächster Schritt?");
        Option escapeCloset = realm.createObject(Option.class);
        escapeCloset.setTitle("Der Raum");
        escapeCloset.setDescription("\n" +
                "Na das bringt doch schon etwas Licht ins dunkel! " +
                "Du bist in einem Zimmer. Erkennen kannst du trotzdem kaum etwas" +
                ", da das Licht aus ist. Du gehst erstmal zum Lichtschalter und betätigst ihn. " +
                "Es bleibt dunkel. Wahrscheinlich defekt. Am anderen Ende des Zimmers siehst du " +
                "einen Schreibtisch auf dem ein Bildschirm diesen beleuchtet. " +
                "Was möchtest du tun?");
        escapeCloset.setHint("gehe aus Schrank");
        Option stayInCloset = realm.createObject(Option.class);
        stayInCloset.setTitle("Erstmal eine Runde chillen");
        stayInCloset.setDescription("Um auf den ganzen Schwachsinn ersteinmal klar zu kommen," +
                "entschließt du dich noch ein paar Minuten im Schrank zu verweilen. Ist ja " +
                "auch gemütlich hier zwischen den ganzen Kleidungsstücken.");
        stayInCloset.setHint("chille im Schrank");
        start.setLeft(escapeCloset);
        start.setRight(stayInCloset);

        escapeCloset.setParent(start);
        stayInCloset.setParent(start);
        escapeCloset.setBackstepBlocked(true);
        stayInCloset.setBackstepBlocked(true);

        game.getOptions().addAll(
                Arrays.asList(
                        start,
                        escapeCloset,
                        stayInCloset));

        game.setCurrentOption(start);

        realm.commitTransaction();
        //return realm.where(Game.class).findFirst();
        return game;
    }
}
