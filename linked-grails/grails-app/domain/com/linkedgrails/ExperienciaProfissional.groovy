package com.linkedgrails

class ExperienciaProfissional extends Experiencia{

    String nivel

    static constraints = {
    }

    static mapping = {
        discriminator "Exp_Prof"
    }
}
