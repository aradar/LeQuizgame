package de.spitak.amazinggame.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.spitak.amazinggame.db.base.Entity;

/**
 * Created by rschlett on 10/28/16.
 */

public class Option implements Entity {

    private long id;
    private long gameId;
    private String title;
    private String description;
    private String hint;
    private String image;
    private Option parent;
    private Option left;
    private Option right;
    private long parentId;
    private long leftChildId;
    private long rightChildId;
    private List<Item> drops;
    private List<Item> requirements;
    private boolean backstepBlocked;
    private boolean completed;

    public Option(String title, String description, String hint,
                  String image, int parentId, int leftChildId, int rightChildId,
                  boolean backstepBlocked, boolean completed) {
        this.title = title;
        this.description = description;
        this.hint = hint;
        this.image = image;
        this.parentId = parentId;
        this.leftChildId = leftChildId;
        this.rightChildId = rightChildId;
        this.backstepBlocked = backstepBlocked;
        this.completed = completed;
    }

    public Option(String title, String description, String hint,
                  String image, Option parent, List<Item> drops,
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

    public Option(int id, int gameId, String title, String description, String hint,
                  String image, int parentId, int leftChildId, int rightChildId,
                  boolean backstepBlocked, boolean completed) {
        this.id = id;
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.hint = hint;
        this.image = image;
        this.parentId = parentId;
        this.leftChildId = leftChildId;
        this.rightChildId = rightChildId;
        this.backstepBlocked = backstepBlocked;
        this.completed = completed;
    }

    public Option(int gameId, String title, String description, String hint,
                  String image, int parentId, int leftChildId, int rightChildId,
                  boolean backstepBlocked, boolean completed) {
        this.gameId = gameId;
        this.title = title;
        this.description = description;
        this.hint = hint;
        this.image = image;
        this.parentId = parentId;
        this.leftChildId = leftChildId;
        this.rightChildId = rightChildId;
        this.backstepBlocked = backstepBlocked;
        this.completed = completed;
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

    public String getImage() {
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

    public long getParentId() {
        return this.parentId;
    }

    public long getLeftChildId() {
        return leftChildId;
    }

    public long getRightChildId() {
        return rightChildId;
    }

    public boolean getBackstepBlocked() {
        return backstepBlocked;
    }

    public boolean getCompleted() {
        return completed;
    }

    @Override
    public long getId() {
        return id;
    }
}
