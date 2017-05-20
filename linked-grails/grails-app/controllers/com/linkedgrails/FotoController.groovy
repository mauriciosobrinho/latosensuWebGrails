package com.linkedgrails

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FotoController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Foto.list(params), model:[fotoCount: Foto.count()]
    }

    def show(Foto foto) {
        respond foto
    }

    def create() {
        respond new Foto(params)
    }

    @Transactional
    def save(Foto foto) {
        if (foto == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (foto.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond foto.errors, view:'create'
            return
        }

        foto.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'foto.label', default: 'Foto'), foto.id])
                redirect foto
            }
            '*' { respond foto, [status: CREATED] }
        }
    }

    def edit(Foto foto) {
        respond foto
    }

    @Transactional
    def update(Foto foto) {
        if (foto == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (foto.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond foto.errors, view:'edit'
            return
        }

        foto.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'foto.label', default: 'Foto'), foto.id])
                redirect foto
            }
            '*'{ respond foto, [status: OK] }
        }
    }

    @Transactional
    def delete(Foto foto) {

        if (foto == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        foto.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'foto.label', default: 'Foto'), foto.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'foto.label', default: 'Foto'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
