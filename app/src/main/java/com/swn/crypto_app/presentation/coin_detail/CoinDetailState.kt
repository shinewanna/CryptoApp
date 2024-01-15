package com.swn.crypto_app.presentation.coin_detail

import com.swn.crypto_app.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: String = ""
)
