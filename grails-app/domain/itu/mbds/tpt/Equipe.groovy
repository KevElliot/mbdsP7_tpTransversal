package itu.mbds.tpt

class Equipe {
    String nom
    Integer note
    static constraints = {
        nom  nullable: false, blank: false
        note  nullable: false, blank: false
    }
    static mapping = {
        id generator: 'sequence', params:[sequence:'EQUIPE_SEQ']
    }
}
