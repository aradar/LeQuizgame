package de.spitak.amazinggame.model;

/**
 * Created by dephiloper on 20.12.16.
 */

public class Loot {
    private int id;
    private int itemId;
    private int gameId;

    public Loot(int _id, int itemId, int gameId) {
        this.id = _id;
        this.itemId = itemId;
        this.gameId = gameId;
    }

    public Loot(int itemId, int gameId) {
        this.itemId = itemId;
        this.gameId = gameId;
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

    public int getId() {
        return id;
    }
}
