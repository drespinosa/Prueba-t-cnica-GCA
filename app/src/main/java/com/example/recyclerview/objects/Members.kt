package com.example.recyclerview.objects

data class Members(
    val id: Int,
    val name: String,
    val lastName: String,
    val urlFoto: String,
    val work: String,
    var gifts: List<String>? = null
)