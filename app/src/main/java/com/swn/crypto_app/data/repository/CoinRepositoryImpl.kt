package com.swn.crypto_app.data.repository

import com.swn.crypto_app.data.remote.CoinPaprikaApi
import com.swn.crypto_app.data.remote.dto.CoinDetailDto
import com.swn.crypto_app.data.remote.dto.CoinDto
import com.swn.crypto_app.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}