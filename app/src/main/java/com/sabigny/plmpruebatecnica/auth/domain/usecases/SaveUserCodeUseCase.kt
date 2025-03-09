package com.sabigny.plmpruebatecnica.auth.domain.usecases

import com.sabigny.plmpruebatecnica.core.data.local.DataStoreManager
import javax.inject.Inject

class SaveUserCodeUseCase @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    suspend operator fun invoke(userCode: String) {
        dataStoreManager.saveUserCode(userCode)
    }
}
