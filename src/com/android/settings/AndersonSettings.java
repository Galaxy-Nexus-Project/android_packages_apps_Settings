package com.android.settings;

/**
 * AndersonSettings.java - Provides a setting for ROM specific things for Anderson
 * 
 * Revision History:
 * 12/12/2011 - Created initial version (termleech)
 * 12/13/2011 - Removed debug statements (termleech)
 **/

import static android.provider.Settings.System.NOTIFICATION_PULSE_COLOR;
import static android.provider.Settings.System.NOTIFICATION_PULSE_COLOR_FALLBACK;

import android.app.ActivityManagerNative;
import android.content.ContentResolver;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.provider.Settings;
import android.util.Log;

public class AndersonSettings extends SettingsPreferenceFragment
	implements Preference.OnPreferenceChangeListener {
	
	private static final String TAG = "AndersonSettings";
	
	private static final String KEY_PULSE_COLOR = "notification_pulse_color";
	
	private ListPreference mPulseColorPreference;
	
	@Override
	public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	    ContentResolver resolver = getActivity().getContentResolver();
	
	    addPreferencesFromResource(R.xml.anderson_settings);
	    
	    mPulseColorPreference = (ListPreference) findPreference(NOTIFICATION_PULSE_COLOR);
	    final int pulseColor = Settings.System.getInt(resolver, NOTIFICATION_PULSE_COLOR, NOTIFICATION_PULSE_COLOR_FALLBACK);
	    mPulseColorPreference.setValue(String.valueOf(pulseColor));
	    mPulseColorPreference.setOnPreferenceChangeListener(this);
	}
	
	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		final String key = preference.getKey();
		ContentResolver resolver = getActivity().getContentResolver();
		
		if (KEY_PULSE_COLOR.equals(key)) {
			int value = Integer.parseInt((String) newValue);
			
			try {
				Settings.System.putInt(resolver, NOTIFICATION_PULSE_COLOR, value);
			} catch (NumberFormatException e) {
				Log.e(TAG, "could not persist notification pulse color setting", e);
			}
		}
		
		return true;
	}
}
