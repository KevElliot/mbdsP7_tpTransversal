package itu.mbds.tpt

class Match {
    Equipe equipe1
    Equipe equipe2
    String resultat
    float cotev1
    float cotev2
    float cotex
    Date datematch
    String lieumatch
    static constraints = {
        equipe1  nullable: true, blank: true
        equipe2  nullable: true, blank: true
        cotev1  nullable: true, blank: true
        cotev2  nullable: true, blank: true
        cotex  nullable: true, blank: true
        resultat  nullable: true, blank: true
        datematch nullable: true, blank: true
        lieumatch nullable: true, blank: true
    }
}
