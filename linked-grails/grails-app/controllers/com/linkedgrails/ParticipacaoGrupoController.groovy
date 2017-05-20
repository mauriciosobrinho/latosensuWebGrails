package com.linkedgrails

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ParticipacaoGrupoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ParticipacaoGrupo.list(params), model:[participacaoGrupoCount: ParticipacaoGrupo.count()]
    }

    def show(ParticipacaoGrupo participacaoGrupo) {
        respond participacaoGrupo
    }

    def create() {
        respond new ParticipacaoGrupo(params)
    }

    @Transactional
    def save(ParticipacaoGrupo participacaoGrupo) {
        if (participacaoGrupo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (participacaoGrupo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond participacaoGrupo.errors, view:'create'
            return
        }

        participacaoGrupo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'participacaoGrupo.label', default: 'ParticipacaoGrupo'), participacaoGrupo.id])
                redirect participacaoGrupo
            }
            '*' { respond participacaoGrupo, [status: CREATED] }
        }
    }

    def edit(ParticipacaoGrupo participacaoGrupo) {
        respond participacaoGrupo
    }

    @Transactional
    def update(ParticipacaoGrupo participacaoGrupo) {
        if (participacaoGrupo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (participacaoGrupo.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond participacaoGrupo.errors, view:'edit'
            return
        }

        participacaoGrupo.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'participacaoGrupo.label', default: 'ParticipacaoGrupo'), participacaoGrupo.id])
                redirect participacaoGrupo
            }
            '*'{ respond participacaoGrupo, [status: OK] }
        }
    }

    @Transactional
    def delete(ParticipacaoGrupo participacaoGrupo) {

        if (participacaoGrupo == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        participacaoGrupo.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'participacaoGrupo.label', default: 'ParticipacaoGrupo'), participacaoGrupo.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'participacaoGrupo.label', default: 'ParticipacaoGrupo'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
