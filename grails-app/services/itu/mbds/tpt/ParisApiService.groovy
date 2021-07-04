package itu.mbds.tpt

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray

@Transactional
class ParisApiService {

    def placeParis(Paris paris) {
        if(paris.detailsparis.size()>0){
            paris.dateparis = new Date()
            paris.gain = null
            paris.nbmatch=paris.detailsparis.size()
            paris.nbgain=0
            paris.nbperdu=0
            def nbcotegloval =1
            paris.detailsparis.each {
                detailsparis ->
                    def match = Match.get(detailsparis.match.id)
                    detailsparis.gain=null
                    switch (detailsparis.prono){
                        case "V1" :
                            detailsparis.cote=match.cotev1
                            break
                        case "X" :
                            detailsparis.cote=match.cotex
                            break
                        case "V2" :
                            detailsparis.cote=match.cotev2
                    }
                    nbcotegloval=nbcotegloval*detailsparis.cote
            }
            paris.coteglobal=nbcotegloval
            paris.gainpossible=paris.coteglobal*paris.mise
            paris.save(failOnError:true)
            paris.errors.allErrors
        }
    }
}
