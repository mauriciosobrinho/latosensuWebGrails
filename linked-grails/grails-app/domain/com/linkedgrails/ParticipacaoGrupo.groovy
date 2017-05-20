package com.linkedgrails

class ParticipacaoGrupo {

    Pessoa pessoa
    Grupo grupo

    static constraints = {

        pessoa unique: ['grupo']
    }
}
