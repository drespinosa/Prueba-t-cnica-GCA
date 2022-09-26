package com.example.recyclerview.objects

data class DetailMembers(
    val id: String,
    val nombre: String,
    val lastName: String,
    val urlFoto: String,
    val cargo: String,
    val lista_de_regalos: List<String>
)