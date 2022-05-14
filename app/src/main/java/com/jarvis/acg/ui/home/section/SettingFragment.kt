package com.jarvis.acg.ui.home.section

import android.os.Bundle
import android.view.View
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.jarvis.acg.R
import com.jarvis.acg.util.DarkModeUtil
import com.jarvis.acg.util.EncryptedPreferenceDataStore
import com.jarvis.acg.util.EncryptedPreferenceDataStore.KEY_API_MODE
import com.jarvis.acg.util.EncryptedPreferenceDataStore.KEY_DARK_MODE
import com.jarvis.acg.util.EncryptedPreferenceDataStore.KEY_I18N_LANG
import com.jarvis.acg.util.EncryptedPreferenceDataStore.putBoolean
import com.jarvis.acg.util.LanguageUtil

class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        preferenceManager.preferenceDataStore = EncryptedPreferenceDataStore

        // Load the preferences from an XML resource
        setPreferencesFromResource(R.xml.preferences, rootKey)
        listView?.overScrollMode = View.OVER_SCROLL_NEVER

        setupDarkMode()
        setupApiMode()
        setupI18N()
    }

    private fun setupApiMode() {
        val component: SwitchPreferenceCompat? = findPreference(KEY_API_MODE)
        component?.apply {
            val isEnableApi = EncryptedPreferenceDataStore.isEnableApi()
            isChecked = isEnableApi
            setOnPreferenceChangeListener { preference, newValue ->
                KEY_API_MODE.putBoolean(newValue.toString().toBoolean())
                isChecked = newValue.toString().toBoolean()
                true
            }
        }
    }

    private fun setupDarkMode() {
        val component: ListPreference? = findPreference(KEY_DARK_MODE)
        component?.apply {
            entries = requireContext().resources.getStringArray(R.array.setting_dark_mode_entries)
            entryValues = DarkModeUtil.ENTRY_VALUES.toTypedArray()
            summary = entries[entryValues.indexOf(DarkModeUtil.getDarkMode())]
            setOnPreferenceChangeListener { preference, newValue ->
                DarkModeUtil.setDarkMode(newValue.toString())
                summary = entries[entryValues.indexOf(newValue)]
                true
            }
        }
    }

    private fun setupI18N() {
        val component: ListPreference? = findPreference(KEY_I18N_LANG)
        component?.apply {
            entries = requireContext().resources.getStringArray(R.array.setting_i18n_entries)
            entryValues = LanguageUtil.ENTRY_VALUES.toTypedArray()
            summary = entries[entryValues.indexOf(LanguageUtil.getLocale())]
            setOnPreferenceChangeListener { preference, newValue ->
                if (newValue is String) {
                    LanguageUtil.setLocale(newValue.toString())
                    summary = entries[entryValues.indexOf(newValue)]
                    requireActivity().recreate()
                }
                true
            }
        }
    }
}