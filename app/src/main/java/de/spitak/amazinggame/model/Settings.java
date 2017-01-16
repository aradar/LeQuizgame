package de.spitak.amazinggame.model;

import io.realm.RealmObject;

/**
 * Created by rschlett on 1/16/17.
 */

public class Settings extends RealmObject {
    private String name;

    public Settings() {
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
