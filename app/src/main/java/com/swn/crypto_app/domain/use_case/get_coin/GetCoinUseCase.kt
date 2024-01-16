package com.swn.crypto_app.domain.use_case.get_coin

import com.swn.crypto_app.common.MsgState
import com.swn.crypto_app.common.Resp
import com.swn.crypto_app.data.remote.dto.toCoinDetail
import com.swn.crypto_app.domain.model.CoinDetail
import com.swn.crypto_app.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resp<CoinDetail>> = flow {
        try {
            emit(Resp(state = MsgState.Loading))
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resp(data = coin, state = MsgState.Data))
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