package itu.mbds.tpt

class Paris {
    String idclient
    String code
    String gain
    Date dateparis
    Float coteglobal
    Double mise
    Integer nbmatch
    Integer nbgain
    Integer nbperdu
    static hasMany = [detailsparis: Detailsparis]
    static constraints = {
        code  nullable: true, blank: true
        gain  nullable: true, blank: true
    }
    static mapping = {
        id generator: 'sequence', params:[sequence:'PARIS_SEQ']
    }
}
