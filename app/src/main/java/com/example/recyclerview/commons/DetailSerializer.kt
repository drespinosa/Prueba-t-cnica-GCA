package com.example.recyclerview.commons

import com.example.recyclerview.base_datos.tables.GiftTable
import com.example.recyclerview.objects.DetailMembers

/**
 * Transforma el id y la lista de regalos del objeto de la aplicación en datos para la base de datos
 */
fun DetailMembers.toData(): List<GiftTable> {
    return this.lista_de_regalos.map { gift ->
        GiftTable(gift = gift, memberId = this.id.toInt())
    }
}