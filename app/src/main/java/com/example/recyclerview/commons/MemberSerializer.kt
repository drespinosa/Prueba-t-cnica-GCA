package com.example.recyclerview.commons

import com.example.recyclerview.data_base.tables.MembersTable
import com.example.recyclerview.objects.Members

fun Members.toData(): MembersTable {
    return MembersTable(
        id = this.id,
        name = this.nombre ?: "",
        lastName = this.lastName ?: "",
        work = this.cargo ?: "",
        url = this.urlFoto ?: ""
    )
}

fun MembersTable.toLocal(): Members {
    return Members(
        id = this.id,
        nombre = this.name,
        lastName = this.lastName,
        cargo = this.work,
        urlFoto = this.url
    )
}