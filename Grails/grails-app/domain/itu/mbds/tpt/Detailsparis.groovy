package itu.mbds.tpt

class Detailsparis {
    Paris paris
    Match match
    String prono
    String gain
    static belongsTo = [paris: Paris]
    static constraints = {
    }
}
