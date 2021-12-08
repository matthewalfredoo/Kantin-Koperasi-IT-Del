package com.kel01.kantinkoperasiitdel.model

data class LoginResponseModel(
    var success: Int?,
    var message: String?,
    var user: UserModel
)
