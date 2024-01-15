package com.swn.crypto_app.domain.use_case.get_coin
import com.swn.crypto_app.common.Resource
import com.swn.crypto_app.data.remote.dto.toCoin
import com.swn.crypto_app.data.remote.dto.toCoinDetail
import com.swn.crypto_app.domain.model.Coin
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
    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("No internet, check your internet connection"))
        }
    }
}