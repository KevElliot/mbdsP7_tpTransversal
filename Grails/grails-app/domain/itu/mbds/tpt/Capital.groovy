package itu.mbds.tpt

class Capital {
    String idclient
    Double capital
    static constraints = {
    }
    static mapping = {
        id generator: 'sequence', params:[sequence:'CAPITAL_SEQ']
    }
}
