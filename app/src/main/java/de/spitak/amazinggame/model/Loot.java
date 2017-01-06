package de.spitak.amazinggame.model;

import android.content.ContentValues;

import com.orm.SugarRecord;

/**
 * Created by dephiloper on 20.12.16.
 */

public class Loot extends SugarRecord implements Entity<Loot> {
    private Option option;
    private Item item;

    public Loot() {
    }

    public Loot(Option option, Item item) {
        this.option = option;
        this.item = item;
    }

    @Override
    public Loot fromContentValueToEntity(ContentValues values) {
        boolean isNotNull = true;
        isNotNull = isNotNull && Option.findById(Option.class, values.getAsLong("option")) != null;
        isNotNull = isNotNull && Item.findById(Item.class, values.getAsLong("item")) != null;

        if (isNotNull)
            return new Loot(Option.findById(Option.class, values.getAsLong("option")),
                    Item.findById(Item.class, values.getAsLong("item")));
        else
            throw new IllegalArgumentException("The column names have to be complete.");
    }
}
