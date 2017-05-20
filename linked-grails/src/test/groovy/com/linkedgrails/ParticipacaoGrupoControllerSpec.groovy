package com.linkedgrails

import grails.test.mixin.*
import spock.lang.*

@TestFor(ParticipacaoGrupoController)
@Mock(ParticipacaoGrupo)
class ParticipacaoGrupoControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.participacaoGrupoList
            model.participacaoGrupoCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.participacaoGrupo!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def participacaoGrupo = new ParticipacaoGrupo()
            participacaoGrupo.validate()
            controller.save(participacaoGrupo)

        then:"The create view is rendered again with the correct model"
            model.participacaoGrupo!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            participacaoGrupo = new ParticipacaoGrupo(params)

            controller.save(participacaoGrupo)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/participacaoGrupo/show/1'
            controller.flash.message != null
            ParticipacaoGrupo.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def participacaoGrupo = new ParticipacaoGrupo(params)
            controller.show(participacaoGrupo)

        then:"A model is populated containing the domain instance"
            model.participacaoGrupo == participacaoGrupo
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def participacaoGrupo = new ParticipacaoGrupo(params)
            controller.edit(participacaoGrupo)

        then:"A model is populated containing the domain instance"
            model.participacaoGrupo == participacaoGrupo
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/participacaoGrupo/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def participacaoGrupo = new ParticipacaoGrupo()
            participacaoGrupo.validate()
            controller.update(participacaoGrupo)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.participacaoGrupo == participacaoGrupo

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            participacaoGrupo = new ParticipacaoGrupo(params).save(flush: true)
            controller.update(participacaoGrupo)

        then:"A redirect is issued to the show action"
            participacaoGrupo != null
            response.redirectedUrl == "/participacaoGrupo/show/$participacaoGrupo.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/participacaoGrupo/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def participacaoGrupo = new ParticipacaoGrupo(params).save(flush: true)

        then:"It exists"
            ParticipacaoGrupo.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(participacaoGrupo)

        then:"The instance is deleted"
            ParticipacaoGrupo.count() == 0
            response.redirectedUrl == '/participacaoGrupo/index'
            flash.message != null
    }
}
