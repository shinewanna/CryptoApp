package com.swn.crypto_app.domain.repository

import com.swn.crypto_app.data.remote.dto.CoinDetailDto
import com.swn.crypto_app.data.remote.dto.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}