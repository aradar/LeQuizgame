package de.spitak.amazinggame.model;

import android.graphics.drawable.BitmapDrawable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rschlett on 10/28/16.
 */

public class Option {
    private String title;
    private String description;
    private String hint;
    private BitmapDrawable image;
    private Option parent;
    private Option left;
    private Option right;
    private List<Item> drops;
    private List<Item> requirements;
    private boolean completed;

    public Option(String title, String description, String hint,
                  BitmapDrawable image, Option parent, List<Item> drops,
                  List<Item> requirements) {
        this.title = title;
        this.description = description;
        this.hint = hint;
        this.image = image;
        this.parent = parent;
        this.drops = drops;
        this.requirements = requirements;
        this.completed = false;
    }

    public Option(String title, String description, String hint) {
        this.title = title;
        this.description = description;
        this.hint = hint;
        this.drops = new ArrayList<>();
        this.requirements = new ArrayList<>();
        this.completed = false;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getHint() {
        return hint;
    }

    public BitmapDrawable getImage() {
        return image;
    }

    public Option getParent() {
        return parent;
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

    public List<Item> getDrops() {
        return drops;
    }

    public List<Item> getRequirements() {
        return requirements;
    }

    public static Option readJson(File file) {
        return null; // TODO: 10/28/16 implement
    }

    public boolean requirementsMet(List<Item> offering) {
        boolean foundAll = true;
        for (int i = 0; i < requirements.size(); i++) {
            if (!offering.contains(requirements.get(i))) {
                foundAll = false;
            }
        }

        return foundAll;
    }

    public boolean isRoot() {
        return parent == null;
    }

    public boolean isChildless() {
        return left == null && right == null;
    }
}
