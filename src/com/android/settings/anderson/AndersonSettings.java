package com.android.settings.anderson;

import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.media.AudioManager;
import android.os.Bundle;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class AndersonSettings extends SettingsPreferenceFragment
	implements OnSharedPreferenceChangeListener {

	@Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        addPreferencesFromResource(R.xml.anderson_settings);

    }

	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		// TODO Auto-generated method stub
		
	}

}
