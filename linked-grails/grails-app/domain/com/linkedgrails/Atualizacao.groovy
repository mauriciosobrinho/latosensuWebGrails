package com.linkedgrails

class Atualizacao {

//    Pessoa responsavel

    Date dateCreated
    Date lastUpdated

    static belongsTo = [responsavel: Pessoa]

    static constraints = {
        dateCreated nullable: true
        lastUpdated nullable: true
    }
}
