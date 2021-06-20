package itu.mbds.tpt

class Detailsparis {
    Match match
    String prono
    String gain
    Float cote
    static belongsTo = [paris: Paris]
    static constraints = {
        gain  nullable: true, blank: true
        paris  nullable: true, blank: true
    }
}
