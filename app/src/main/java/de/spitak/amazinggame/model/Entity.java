package de.spitak.amazinggame.model;

import android.content.ContentValues;

/**
 * Created by rschlett on 12/31/16.
 */

public interface Entity<T> {
    T fromContentValueToEntity(ContentValues values);

}
