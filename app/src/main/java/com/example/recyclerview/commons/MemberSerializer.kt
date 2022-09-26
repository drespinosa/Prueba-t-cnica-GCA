package com.example.recyclerview.commons

import com.example.recyclerview.data_base.tables.MembersTable
import com.example.recyclerview.objects.Members

fun Members.toData(): MembersTable {
    return MembersTable(
        id = this.id,
        name = this.name,
        lastName = this.lastName,
        work = this.work,
        url = this.urlFoto
    )
}

fun MembersTable.toLocal(): Members {
    return Members(
        id = this.id,
        name = this.name,
        lastName = this.lastName,
        work = this.work,
        urlFoto = this.url
    )
}