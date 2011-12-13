package com.android.settings.anderson;

import android.content.ContentResolver;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;

public class AndersonSettings extends SettingsPreferenceFragment
	implements Preference.OnPreferenceChangeListener {
	
	private static final String TAG = "AndersonSettings";

	@Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        
        //ContentResolver resolver = getContentResolver();

        addPreferencesFromResource(R.xml.anderson_settings);
        
        Log.e(TAG, "Initialized anderson settings window");
    }

	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		// TODO Auto-generated method stub
		return false;
	}

}
