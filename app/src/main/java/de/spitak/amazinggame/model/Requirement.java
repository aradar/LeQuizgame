package de.spitak.amazinggame.model;

import de.spitak.amazinggame.db.base.Entity;

/**
 * Created by dephiloper on 20.12.16.
 */

// TODO: 12/30/16 why the fuck is the gameId here?
public class Requirement implements Entity {
    private long id;
    private long itemId;
    private long gameId;

    public Requirement(int id, int itemId, int gameId) {
        this.id = id;
        this.itemId = itemId;
        this.gameId = gameId;
    }

    public Requirement(int itemId, int gameId) {
        this.itemId = itemId;
        this.gameId = gameId;
    }

    @Override
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
