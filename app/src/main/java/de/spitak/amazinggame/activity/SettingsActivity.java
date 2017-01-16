package de.spitak.amazinggame.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import de.spitak.amazinggame.R;
import de.spitak.amazinggame.databinding.ActivitySettingsBinding;
import de.spitak.amazinggame.viewmodel.SettingsViewModel;

public class SettingsActivity extends AppCompatActivity {

    private SettingsViewModel settingsViewModel;
    private ActivitySettingsBinding activitySettingsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySettingsBinding = DataBindingUtil.setContentView(this, R.layout.activity_settings);

        settingsViewModel = new SettingsViewModel();
        activitySettingsBinding.setSettings(settingsViewModel);

        findViewById(R.id.settings_name);
    }
}
