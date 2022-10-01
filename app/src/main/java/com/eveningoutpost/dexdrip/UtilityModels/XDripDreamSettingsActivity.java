package com.eveningoutpost.dexdrip.UtilityModels;

import android.os.Bundle;

import com.eveningoutpost.dexdrip.BaseAppCompatActivity;
import com.eveningoutpost.dexdrip.databinding.ActivityXdripDreamSettingsBinding;
import com.eveningoutpost.dexdrip.xutilitymodels.PrefsViewImpl;

public class XDripDreamSettingsActivity extends BaseAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActivityXdripDreamSettingsBinding binding = ActivityXdripDreamSettingsBinding.inflate(getLayoutInflater());
        binding.setPrefs(new PrefsViewImpl());
        setContentView(binding.getRoot());

    }

}
