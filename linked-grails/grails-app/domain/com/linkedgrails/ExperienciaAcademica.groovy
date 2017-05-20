package com.linkedgrails

class ExperienciaAcademica extends Experiencia {

    String grau

    static constraints = {
    }

    static mapping = {
        discriminator "Experiência Acadêmica"
    }
}
