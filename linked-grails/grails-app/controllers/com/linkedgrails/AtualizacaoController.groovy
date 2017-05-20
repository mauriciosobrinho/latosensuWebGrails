package com.linkedgrails

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AtualizacaoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Atualizacao.list(params), model:[atualizacaoCount: Atualizacao.count()]
    }

    def show(Atualizacao atualizacao) {
        respond atualizacao
    }

    def create() {
        respond new Atualizacao(params)
    }

    @Transactional
    def save(Atualizacao atualizacao) {
        if (atualizacao == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (atualizacao.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond atualizacao.errors, view:'create'
            return
        }

        atualizacao.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'atualizacao.label', default: 'Atualizacao'), atualizacao.id])
                redirect atualizacao
            }
            '*' { respond atualizacao, [status: CREATED] }
        }
    }

    def edit(Atualizacao atualizacao) {
        respond atualizacao
    }

    @Transactional
    def update(Atualizacao atualizacao) {
        if (atualizacao == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (atualizacao.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond atualizacao.errors, view:'edit'
            return
        }

        atualizacao.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'atualizacao.label', default: 'Atualizacao'), atualizacao.id])
                redirect atualizacao
            }
            '*'{ respond atualizacao, [status: OK] }
        }
    }

    @Transactional
    def delete(Atualizacao atualizacao) {

        if (atualizacao == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        atualizacao.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'atualizacao.label', default: 'Atualizacao'), atualizacao.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'atualizacao.label', default: 'Atualizacao'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
