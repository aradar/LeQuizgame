package de.spitak.amazinggame.model;

import android.content.ContentValues;

import com.orm.SugarRecord;

/**
 * Created by rschlett on 12/30/16.
 */

public class Inventory extends SugarRecord implements Entity<Inventory> {
    private Game game;
    private Item item;

    public Inventory() {
    }

    public Inventory(Game game, Item item) {
        this.game = game;
        this.item = item;
    }

    @Override
    public Inventory fromContentValueToEntity(ContentValues values) {
        boolean isNotNull = true;
        isNotNull = isNotNull && Game.findById(Game.class, values.getAsInteger("game")) != null;
        isNotNull = isNotNull && Item.findById(Item.class, values.getAsInteger("item")) != null;

        if (isNotNull)
            return new Inventory(Game.findById(Game.class, values.getAsInteger("game")),
                    Item.findById(Item.class, values.getAsInteger("item")));
        else
            throw new IllegalArgumentException("The column names have to be complete.");
    }
}
