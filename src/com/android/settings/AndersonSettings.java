package com.android.settings;

import static android.provider.Settings.System.NOTIFICATION_PULSE_COLOR;
import static android.provider.Settings.System.NOTIFICATION_PULSE_COLOR_FALLBACK;

import android.content.ContentResolver;
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
		
		Log.w(TAG, "Got key: " + key);
		
		if (KEY_PULSE_COLOR.equals(key)) {
			int value = Integer.parseInt((String) newValue);
			
			Log.w(TAG, "Setting pulse color: " + value);
			
			try {
				Settings.System.putInt(resolver, NOTIFICATION_PULSE_COLOR, value);
			} catch (NumberFormatException e) {
				Log.e(TAG, "could not persist notification pulse color setting", e);
			}
		}
		
		return true;
	}
}
