package com.example.recyclerview.repository

import com.example.recyclerview.base_datos.tables.MembersTable
import com.example.recyclerview.objects.Members

/**
 * Transforma el objeto a datos para la base de datos
 */
fun Members.toData(): MembersTable {
    return MembersTable(
        id = this.id,
        name = this.nombre,
        lastName = this.lastName,
        work = this.cargo,
        url = this.urlFoto
    )
}

/**
 * Transforma datos de la base de datos a un objeto
 */
fun MembersTable.toLocal(): Members {
    return Members(
        id = this.id,
        nombre = this.name,
        lastName = this.lastName,
        cargo = this.work,
        urlFoto = this.url
    )
}