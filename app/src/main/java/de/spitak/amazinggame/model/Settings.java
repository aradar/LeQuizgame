package de.spitak.amazinggame.model;

import de.spitak.amazinggame.activity.MenuActivity;
import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by rschlett on 1/16/17.
 */

public class Settings extends RealmObject {
    private String name;
    private static Settings settings;

    public Settings() {
    }

    public static Settings getInstance() {
        Realm.init(MenuActivity.getAppContext());
        Realm realm = Realm.getDefaultInstance();

        if (settings == null) {
            settings = realm.where(Settings.class).findFirst();
            if (settings == null) {
                realm.beginTransaction();
                settings = realm.createObject(Settings.class);
                realm.commitTransaction();
            }
        }

        return settings;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
