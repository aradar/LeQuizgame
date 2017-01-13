package de.spitak.amazinggame.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by rschlett on 10/28/16.
 */

public class Option extends RealmObject implements Entity<Option> {

    private String title;
    private String description;
    private String hint;
    private String image;
    private Option parent;
    private Option left;
    private Option right;
    private boolean backstepBlocked;
    private boolean completed;
    private RealmList<Item> loot;
    private RealmList<Item> requirement;

    public Option() {
        loot = new RealmList<>();
        requirement = new RealmList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isBackstepBlocked() {
        return backstepBlocked;
    }

    public void setBackstepBlocked(boolean backstepBlocked) {
        this.backstepBlocked = backstepBlocked;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Option getParent() {
        return parent;
    }

    public void setParent(Option parent) {
        this.parent = parent;
    }

    public Option getLeft() {
        return left;
    }

    public void setLeft(Option left) {
        this.left = left;
    }

    public Option getRight() {
        return right;
    }

    public void setRight(Option right) {
        this.right = right;
    }

    public RealmList<Item> getLoot() {
        return loot;
    }

    public void setLoot(RealmList<Item> loot) {
        this.loot = loot;
    }

    public RealmList<Item> getRequirements() {
        return requirement;
    }

    public void setRequirement(RealmList<Item> requirement) {
        this.requirement = requirement;
    }

    public boolean isRoot() {
        return this.parent == null;
    }


}
