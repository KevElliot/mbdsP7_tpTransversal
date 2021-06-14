package itu.mbds.tpt

class Equipe {
    String nom
    Integer note
    static constraints = {
        nom  nullable: false, blank: false
    }
}
