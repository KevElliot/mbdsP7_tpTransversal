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
    Integer fini
    Integer but1
    Integer but2
    static constraints = {
        equipe1  nullable: true, blank: true
        equipe2  nullable: true, blank: true
        cotev1  nullable: true, blank: true
        cotev2  nullable: true, blank: true
        cotex  nullable: true, blank: true
        resultat  nullable: true, blank: true
        datematch nullable: true, blank: true
        lieumatch nullable: true, blank: true
        but1 nullable: true, blank: true
        but2 nullable: true, blank: true
    }
    static mapping = {
        id generator: 'sequence', params:[sequence:'MATCH_SEQ']
        equipe1 lazy: false
        equipe2 lazy: false
    }
    Integer getMatchEquipe1Count(Equipe equipe) {
        Match.countByEquipe1(equipe)
    }
    Integer getMatchEquipe2Count(Equipe equipe) {
        Match.countByEquipe2(equipe)
    }

}
