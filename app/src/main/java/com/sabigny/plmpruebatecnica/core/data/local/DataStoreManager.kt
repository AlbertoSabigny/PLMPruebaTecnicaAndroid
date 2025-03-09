package com.sabigny.plmpruebatecnica.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        private val USER_CODE_KEY = stringPreferencesKey("user_code")
    }

    suspend fun saveUserCode(userCode: String) {
        dataStore.edit { preferences ->
            preferences[USER_CODE_KEY] = userCode
        }
    }

    fun getUserCode(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[USER_CODE_KEY]
        }
    }
}
