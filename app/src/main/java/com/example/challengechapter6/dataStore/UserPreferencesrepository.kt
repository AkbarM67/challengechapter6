package com.example.challengechapter6.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import java.util.prefs.Preferences

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_pref")


class UserPreferencesrepository(private val context: Context){

    val username = stringPreferencys


}
