package com.linkedgrails

class Pessoa {

    String nome
    String estadoCivil
    Date dataNascimento
    Integer idade

    static hasMany = [atualizacoes: Atualizacao,
                      participacoes: ParticipacaoGrupo]

    static hasOne = [foto: Foto]

    static constraints = {
        nome maxSize: 10
        foto nullable: true
    }

    @Override
    String toString() {
        return "${nome} ${idade}"
    }
}
