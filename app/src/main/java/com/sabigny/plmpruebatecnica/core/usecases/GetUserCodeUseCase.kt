package com.sabigny.plmpruebatecnica.core.usecases

import com.sabigny.plmpruebatecnica.core.data.local.DataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserCodeUseCase @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    operator fun invoke(): Flow<String?> {
        return dataStoreManager.getUserCode()
    }
}
