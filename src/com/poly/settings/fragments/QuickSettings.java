package com.poly.settings.fragments;

import android.os.Bundle;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.support.v14.preference.SwitchPreference;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;

import com.android.settings.R;
import com.poly.settings.preferences.CustomSeekBarPreference;
import com.android.settings.SettingsPreferenceFragment;
import com.android.settings.Utils;

import com.android.internal.logging.MetricsProto.MetricsEvent;


public class QuickSettings extends SettingsPreferenceFragment
        implements Preference.OnPreferenceChangeListener {

    private static final String PREF_SYSUI_QQS_COUNT = "sysui_qqs_count_key";

    private CustomSeekBarPreference mSysuiQqsCount;

    @Override
    protected int getMetricsCategory() {
        return 420;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.quick_settings);
	ContentResolver resolver = getActivity().getContentResolver();

        mSysuiQqsCount = (CustomSeekBarPreference) findPreference(PREF_SYSUI_QQS_COUNT);
        int SysuiQqsCount = Settings.Secure.getInt(resolver,
               Settings.Secure.QQS_COUNT, 5);
	mSysuiQqsCount.setValue(SysuiQqsCount);
	mSysuiQqsCount.setOnPreferenceChangeListener(this);
        }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        ContentResolver resolver = getActivity().getContentResolver();
        if (preference == mSysuiQqsCount) {
	    int SysuiQqsCountValue = (Integer) newValue;
            Settings.Secure.putInt(resolver,
                   Settings.Secure.QQS_COUNT, SysuiQqsCountValue);
            return true;
        }
        return false;
    }
}
