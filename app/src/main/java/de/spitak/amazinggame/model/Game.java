package de.spitak.amazinggame.model;

import android.content.Context;
import android.os.Vibrator;
import android.widget.Toast;

import java.util.Arrays;

import de.spitak.amazinggame.R;
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
    private Vibrator v;

    // Start without a delay
    // Vibrate for 100 milliseconds
    // Sleep for 1000 milliseconds
    @Ignore
    private long[] vibrationPattern = {0, 200, 200, 200, 200};


    @Ignore
    private boolean completed;

    // TODO: 1/16/17 can be changed later
    @Ignore
    private int movesTaken;

    @Ignore
    private Option currentOption;

    static {
        Realm.init(MenuActivity.getAppContext());
    }

    public Game() {
        options = new RealmList<>();
        v = (Vibrator) MenuActivity.getAppContext().getSystemService(Context.VIBRATOR_SERVICE);
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
            currentOption = option;
            collectLoot();
        }
    }

    private void collectLoot() {
        if (currentOption.getLoot() != null) {
            Realm realm = Realm.getDefaultInstance();
            for (Item item : currentOption.getLoot()) {
                if (!inventory.contains(item)) {
                    realm.beginTransaction();
                    inventory.add(item);
                    // Todo ugly!
                    Toast.makeText(MenuActivity.getAppContext(), String.format("Du hast %s " +
                                    "aufgesammelt.", item.getName()),
                            Toast.LENGTH_SHORT).show();
                    realm.commitTransaction();
                    v.vibrate(500);
                }
            }
        }
    }

    private boolean requirementsMet(RealmList<Item> requirements) {
        if (requirements != null) {
            for (Item requirement : requirements) {
                if (!inventory.contains(requirement)) {

                    // Todo ugly!
                    Toast.makeText(MenuActivity.getAppContext(), String.format("Du benötigst %s " +
                            "um weiter zu kommen.", requirement.getName()),
                            Toast.LENGTH_SHORT).show();

                    v.vibrate(vibrationPattern, -1);
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
}
