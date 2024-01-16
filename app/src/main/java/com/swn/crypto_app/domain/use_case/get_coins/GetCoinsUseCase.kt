package com.swn.crypto_app.domain.use_case.get_coins

import com.swn.crypto_app.common.MsgState
import com.swn.crypto_app.common.Resp
import com.swn.crypto_app.data.remote.dto.toCoin
import com.swn.crypto_app.domain.model.Coin
import com.swn.crypto_app.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resp<List<Coin>>> = flow {
        try {
            emit(Resp(state = MsgState.Loading))
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resp(data = coins, state = MsgState.Data))
        } catch (e: HttpException) {
            emit(Resp(data = "An unexpected error occurred", state = MsgState.Error))
        } catch (e: IOException) {
            emit(
                Resp(
                    data = "No internet, check your internet connection",
                    state = MsgState.Error
                )
            )
        }
    }
}