package com.linkedgrails

class Foto {

    Pessoa pessoa

    byte[] conteudo

    Date dateCreated
    Date lastUpdated

    static constraints = {
        conteudo sqlType: "longblob"
    }
}
