package com.swn.crypto_app.presentation.coin_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.swn.crypto_app.presentation.coin_detail.components.CoinTag
import com.swn.crypto_app.presentation.coin_detail.components.TeamListItem
import com.swn.crypto_app.presentation.theme.Green80

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel() // hiltViewModel will find the CoinListViewModel automatically from di
) {
    val state = viewModel.state.value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 15.dp)
    ) {
        state.coin?.let { coin ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Box(modifier = Modifier.width((LocalConfiguration.current.screenWidthDp / 1.5).dp)) {
                            Text(
                                text = "${coin.rank}. ${coin.name} (${coin.symbol})",
                                style = MaterialTheme.typography.headlineMedium,

                                )
                        }
                        Text(
                            text = if (coin.isActive) "active" else "inactive",
                            color = if (coin.isActive) Green80 else Color.Red,
                            fontStyle = FontStyle.Italic,
                            textAlign = TextAlign.End,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(2f)
                        )
                    }
                    if (coin.description.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = coin.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    if (coin.tags.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(15.dp))
                        Text(
                            text = "Tags",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        FlowRow(
                            modifier = Modifier
                                .fillMaxWidth(),
                        ) {
                            coin.tags.forEach { tag ->
                                Box(
                                    modifier = Modifier.padding(
                                        end = 10.dp,
                                        bottom = 5.dp,
                                        top = 5.dp
                                    )
                                ) {
                                    CoinTag(tag = tag)
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    if (coin.team.isNotEmpty()) {
                        Text(
                            text = "Team members",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                }
                items(coin.team) { teamMember ->
                    TeamListItem(
                        teamMember = teamMember,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 10.dp, horizontal = 3.dp)
                    )
                    Divider()
                }

            }
        }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error, color = MaterialTheme.colorScheme.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

    }
}