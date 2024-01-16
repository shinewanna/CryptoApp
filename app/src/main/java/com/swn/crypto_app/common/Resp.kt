package com.swn.crypto_app.common

enum class MsgState {
    Loading,
    Data,
    Error,
}

// I use Resp as Resource
class Resp<T>(
    var data: Any? = null,
    var state: MsgState = MsgState.Loading
)
