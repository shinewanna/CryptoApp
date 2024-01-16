package com.swn.crypto_app.presentation.coin_list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.swn.crypto_app.common.Output
import com.swn.crypto_app.presentation.Screen
import com.swn.crypto_app.presentation.coin_list.components.CoinListItem

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel() // hiltViewModel will find the CoinListViewModel automatically
) {
    val resp = viewModel.resp.value
    Output(
        resp = resp,
        onData = { data ->
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Spacer(modifier = Modifier.height(7.dp))
                }
                items(data) { coin ->
                    CoinListItem(coin = coin, onItemClick = {
                        navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
                    })
                }
            }
        },
    )
}