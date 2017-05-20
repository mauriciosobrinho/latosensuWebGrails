package com.linkedgrails

class Grupo {

    String nome

    static hasMany = [participacoes: ParticipacaoGrupo]


    static transients = [integrantes: ArrayList]

    ArrayList<String> getIntegrantes() {
        println "GET"
        ArrayList<String> participantes =
                this.participacoes.collect { participacao ->
                    println participacao.pessoa.nome
                    participacao.pessoa.nome
                }
        return participantes
    }

    static constraints = {
    }
}
