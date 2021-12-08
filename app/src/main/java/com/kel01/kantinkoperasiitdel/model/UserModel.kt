package com.kel01.kantinkoperasiitdel.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("t_c_noKTP")
    @Expose
    var noKTP: String?,

    @SerializedName("t_c_role")
    @Expose
    var role: String?,

    @SerializedName("t_c_namaLengkap")
    @Expose
    var namaLengkap: String?,

    @SerializedName("t_c_noHP")
    @Expose
    var noHP: String?,

    @SerializedName("t_c_username")
    @Expose
    var username: String?,

    @SerializedName("t_c_password")
    var password: String?
)
