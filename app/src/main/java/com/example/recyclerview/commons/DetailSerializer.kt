package com.example.recyclerview.commons

import com.example.recyclerview.data_base.tables.GiftTable
import com.example.recyclerview.objects.DetailMember

fun DetailMember.toData(): List<GiftTable> {
    return this.lista_de_regalos.map { gift ->
        GiftTable(gift = gift, memberId = this.id.toInt())
    }
}