package com.android.settings;

/**
 * AndersonSettings.java - Provides a setting for ROM specific things for Anderson
 * 
 * Revision History:
 * 12/12/2011 - Created initial version (termleech)
 * 12/13/2011 - Removed debug statements (termleech)
 * 12/14/2011 - Added settings for pulse duration and frequency (termleech)
 **/

import static android.provider.Settings.System.NOTIFICATION_PULSE_COLOR;
import static android.provider.Settings.System.NOTIFICATION_PULSE_COLOR_FALLBACK;
import static android.provider.Settings.System.NOTIFICATION_PULSE_DURATION;
import static android.provider.Settings.System.NOTIFICATION_PULSE_DURATION_FALLBACK;
import static android.provider.Settings.System.NOTIFICATION_PULSE_FREQUENCY;
import static android.provider.Settings.System.NOTIFICATION_PULSE_FREQUENCY_FALLBACK;

import android.app.ActivityManagerNative;
import android.content.ContentResolver;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.provider.Settings;
import android.util.Log;

public class AndersonNotificationSettings extends SettingsPreferenceFragment
	implements Preference.OnPreferenceChangeListener {
	
	private static final String TAG = "AndersonNotificationSettings";
	
	private ListPreference mPulseColorPreference;
	private ListPreference mPulseDurationPreference;
	private ListPreference mPulseFrequencyPreference;
	
	@Override
	public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	    ContentResolver resolver = getActivity().getContentResolver();
	    final int pulseColor = Settings.System.getInt(resolver, NOTIFICATION_PULSE_COLOR, NOTIFICATION_PULSE_COLOR_FALLBACK);
	    final int pulseDuration = Settings.System.getInt(resolver, NOTIFICATION_PULSE_DURATION, NOTIFICATION_PULSE_DURATION_FALLBACK);
	    final int pulseFrequency = Settings.System.getInt(resolver, NOTIFICATION_PULSE_FREQUENCY, NOTIFICATION_PULSE_FREQUENCY_FALLBACK);
	
	    addPreferencesFromResource(R.xml.anderson_notification_settings);
	    
	    mPulseColorPreference = (ListPreference) findPreference(NOTIFICATION_PULSE_COLOR);
	    mPulseColorPreference.setValue(String.valueOf(pulseColor));
	    mPulseColorPreference.setOnPreferenceChangeListener(this);
	    
	    mPulseDurationPreference = (ListPreference) findPreference(NOTIFICATION_PULSE_DURATION);
	    mPulseDurationPreference.setValue(String.valueOf(pulseDuration));
	    mPulseDurationPreference.setOnPreferenceChangeListener(this);
	    
	    mPulseFrequencyPreference = (ListPreference) findPreference(NOTIFICATION_PULSE_FREQUENCY);
	    mPulseFrequencyPreference.setValue(String.valueOf(pulseFrequency));
	    mPulseFrequencyPreference.setOnPreferenceChangeListener(this);
	}
	
	@Override
	public boolean onPreferenceChange(Preference preference, Object newValue) {
		final String key = preference.getKey();
		int value = 0;
		ContentResolver resolver = getActivity().getContentResolver();
		
		if (NOTIFICATION_PULSE_COLOR.equals(key)) {
			value = Integer.parseInt((String) newValue);
			
			try {
				Settings.System.putInt(resolver, NOTIFICATION_PULSE_COLOR, value);
			} catch (NumberFormatException e) {
				Log.e(TAG, "could not persist notification pulse color setting", e);
			}
		} else if (NOTIFICATION_PULSE_DURATION.equals(key)) {
			value = Integer.parseInt((String) newValue);
			
			try {
				Settings.System.putInt(resolver, NOTIFICATION_PULSE_DURATION, value);
			} catch (NumberFormatException e) {
				Log.e(TAG, "could not persist notification pulse duration setting", e);
			}
		} else if (NOTIFICATION_PULSE_FREQUENCY.equals(key)) {
			value = Integer.parseInt((String) newValue);
			
			try {
				Settings.System.putInt(resolver, NOTIFICATION_PULSE_FREQUENCY, value);
			} catch (NumberFormatException e) {
				Log.e(TAG, "could not persist notification pulse frequency setting", e);
			}
		}
		
		return true;
	}
}
