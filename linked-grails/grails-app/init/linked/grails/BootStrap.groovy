package linked.grails

import com.linkedgrails.Atualizacao
import com.linkedgrails.ExperienciaAcademica
import com.linkedgrails.ExperienciaProfissional
import com.linkedgrails.Grupo
import com.linkedgrails.ParticipacaoGrupo
import com.linkedgrails.Pessoa

class BootStrap {

    def init = { servletContext ->
        adicionarPessoas()

        adicionarGrupos()

        Long pessoaId = 5
        Long grupoId = 3

        criarParticipacaoGrupo(grupoId, pessoaId)
        criarParticipacaoGrupo(1, pessoaId)
        criarParticipacaoGrupo(2, pessoaId)
        criarParticipacaoGrupo(4, pessoaId)

        Grupo grupo = Grupo.get(1)
        grupo.refresh()
        println grupo.integrantes

//        Pessoa joao = Pessoa.findByNome('João')
//        println "A idade de João é ${joao.idade}"
//        println "João fez ${joao.atualizacoes.size()}"
//        joao.atualizacoes.each{ Atualizacao atualizacaoAtual ->
//            println "${atualizacaoAtual.id} foi criada em ${atualizacaoAtual.dateCreated}"
//        }

        //joao.addToAtualizacoes(new Atualizacao())
//        new Atualizacao(responsavel: joao).save(flush: true, failOnError: true)


        println "Encontrou a atualizacao ${Atualizacao.get(35)}"
//        joao.removeFromAtualizacoes(Atualizacao.get(35))
//        joao.save(flush: true)
//        Atualizacao.findById(35)


        new ExperienciaAcademica(
                inicio: new Date(), nome: "Experiencia A1",
                pessoa: Pessoa.get(1), descricao: "A",
                grau: "Superior"
        ).save(flush: true, failOnError: true)

        new ExperienciaProfissional(
                inicio: new Date(), nome: "Experiencia P1",
                pessoa: Pessoa.get(1), descricao: "A",
                nivel: "Pleno"
        ).save(flush: true, failOnError: true)
    }

    private ParticipacaoGrupo criarParticipacaoGrupo(long grupoId, long pessoaId) {
        new ParticipacaoGrupo(
                grupo: Grupo.findById(grupoId),
                pessoa: Pessoa.get(pessoaId)
        ).save(flush: true, failOnError: true)
    }

    private adicionarGrupos() {
        7.times { i ->
            Grupo grupo = new Grupo(nome: "Grupo ${i}")
            grupo.save(failOnError: true, flush: true)
        }
    }

    private adicionarPessoas() {
        15.times { i ->
            def pessoa = new Pessoa(
                    nome: "Pessoa ${i}", idade: "40",
                    dataNascimento: new Date(),
                    estadoCivil: "Solteiro"
            )

            pessoa.save(failOnError: true, flush: true)
        }
    }
    def destroy = {

    }
}
