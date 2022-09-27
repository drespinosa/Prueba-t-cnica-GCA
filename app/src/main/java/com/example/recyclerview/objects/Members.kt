package com.example.recyclerview.objects

data class Members(
    val id: Int,
    val nombre: String,
    val lastName: String,
    val urlFoto: String,
    val cargo: String,
    var gifts: List<String>? = null
)