package com.linkedgrails

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ExperienciaController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Experiencia.list(params), model:[experienciaCount: Experiencia.count()]
    }

    def show(Experiencia experiencia) {
        respond experiencia
    }

    def create() {
//        respond new Experiencia(params)
    }

    @Transactional
    def save(Experiencia experiencia) {
        if (experiencia == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (experiencia.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond experiencia.errors, view:'create'
            return
        }

        experiencia.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'experiencia.label', default: 'Experiencia'), experiencia.id])
                redirect experiencia
            }
            '*' { respond experiencia, [status: CREATED] }
        }
    }

    def edit(Experiencia experiencia) {
        respond experiencia
    }

    @Transactional
    def update(Experiencia experiencia) {
        if (experiencia == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (experiencia.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond experiencia.errors, view:'edit'
            return
        }

        experiencia.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'experiencia.label', default: 'Experiencia'), experiencia.id])
                redirect experiencia
            }
            '*'{ respond experiencia, [status: OK] }
        }
    }

    @Transactional
    def delete(Experiencia experiencia) {

        if (experiencia == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        experiencia.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'experiencia.label', default: 'Experiencia'), experiencia.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'experiencia.label', default: 'Experiencia'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
