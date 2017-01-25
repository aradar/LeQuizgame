package de.spitak.amazinggame.viewmodel;

import android.databinding.BaseObservable;
import android.text.Editable;
import android.text.TextWatcher;

import de.spitak.amazinggame.activity.MenuActivity;
import de.spitak.amazinggame.model.Settings;
import io.realm.Realm;

/**
 * Created by rschlett on 1/16/17.
 */

public class SettingsViewModel extends BaseObservable {
    private Settings settings;
    private Realm realm;

    public TextWatcher watcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (!getName().equals(editable.toString())) {
                setName(editable.toString());
            }
        }
    };

    public SettingsViewModel() {
        Realm.init(MenuActivity.getAppContext());
        realm = Realm.getDefaultInstance();

        settings = realm.where(Settings.class).findFirst();
        if (settings == null) {
            realm.beginTransaction();
            settings = realm.createObject(Settings.class);
            realm.commitTransaction();
        }
    }

    public String getName() {
        return settings.getName();
    }

    public void setName(String name) {
        realm.beginTransaction();
        settings.setName(name);
        realm.commitTransaction();
        notifyChange();
    }

    public Settings getSettings() {
        return settings;
    }
}
