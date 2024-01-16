@file:Suppress("UNCHECKED_CAST")

package com.swn.crypto_app.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun <T> Output(
    resp: Resp<T>,
    onData: @Composable (T) -> Unit,
    onLoading: (() -> Unit)? = null,
    onError: ((String) -> Unit)? = null,
    nthOnLoading: Boolean = false,
    nthOnError: Boolean = false,
) {
    when (resp.state) {
        MsgState.Loading -> {
            if (onLoading == null) {
                DefaultOnLoading(nthOnLoading)
            } else {
                onLoading()
            }
        }

        MsgState.Data -> onData(resp.data as T)
        MsgState.Error -> {
            if (onError == null) {
                DefaultOnError(nthOnError, resp.data.toString())
            } else {
                onError(resp.data.toString())
            }
        }
    }
}

@Composable
fun DefaultOnError(nthOnError: Boolean, errorMessage: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (!nthOnError) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Text(
                    text = errorMessage, color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(20.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun DefaultOnLoading(nthOnLoading: Boolean) {
    Box(modifier = Modifier.fillMaxSize()) {
        if (!nthOnLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}