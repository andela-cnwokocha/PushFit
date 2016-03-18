package com.example.andela.fitgoup.utils;

/**
 * Created by andela on 3/18/16.
 */

import android.content.Context;
import android.support.v7.preference.DialogPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.view.View;
import android.widget.TimePicker;

public class TimePickerDialogFragmentCompat extends PreferenceDialogFragmentCompat
    implements DialogPreference.TargetFragment {
  TimePicker timePicker = null;

  @Override
  protected View onCreateDialogView(Context context) {
    timePicker = new TimePicker(context);
    return (timePicker);
  }

  @Override
  protected void onBindDialogView(View v) {
    super.onBindDialogView(v);
    timePicker.setIs24HourView(true);
    TimePreferencePicker pref = (TimePreferencePicker) getPreference();
    timePicker.setCurrentHour(pref.hour);
    timePicker.setCurrentMinute(pref.minute);
  }

  @Override
  public void onDialogClosed(boolean positiveResult) {
    if (positiveResult) {
      TimePreferencePicker pref = (TimePreferencePicker) getPreference();
      pref.hour = timePicker.getCurrentHour();
      pref.minute = timePicker.getCurrentMinute();

      String value = TimePreferencePicker.timeToString(pref.hour, pref.minute);
      if (pref.callChangeListener(value)) pref.persistStringValue(value);
    }
  }

  @Override
  public Preference findPreference(CharSequence charSequence) {
    return getPreference();
  }
}