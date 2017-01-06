package de.spitak.amazinggame.viewmodel;

import android.databinding.BaseObservable;

import java.util.List;

import de.spitak.amazinggame.model.Item;
import de.spitak.amazinggame.model.Option;

/**
 * Created by rschlett on 10/28/16.
 */

public class OptionViewModel extends BaseObservable {
    private Option option;

    public OptionViewModel(Option option) {
        this.option = option;
    }

    public String getDescription() {
        //return option.getDescription();
        return null;
    }

    public String getLeftHint() {
        //return option.getLeft() != null ? option.getLeft().getHint() : "";
        return null;
    }

    public String getRightHint() {
        //return option.getRight() != null ? option.getRight().getHint() : "";
        return null;
    }

    public String getTitle() {
        //return option.getTitle();
        return null;
    }

    public List<Item> getDrops() {
        //return option.getDrops();
        return null;
    }

    public String getImage() {
        //return option.getImage();
        return null;
    }

    public List<Item> getRequirements() {
        //return option.getRequirements();
        return null;
    }
}
