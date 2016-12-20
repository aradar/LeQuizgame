package de.spitak.amazinggame.model;

/**
 * Created by dephiloper on 20.12.16.
 */

public class Requirement {
    private int id;
    private int itemId;
    private int gameId;

    public Requirement(int id, int itemId, int gameId) {
        this.id = id;
        this.itemId = itemId;
        this.gameId = gameId;
    }

    public Requirement(int itemId, int gameId) {
        this.itemId = itemId;
        this.gameId = gameId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
