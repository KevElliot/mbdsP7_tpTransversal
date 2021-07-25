package itu.mbds.tpt

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray
import java.text.DecimalFormat;

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
            DecimalFormat df = new DecimalFormat("#.00");
            String coteglobal=df.format(nbcotegloval).replace(',','.')
            paris.coteglobal=Float.parseFloat(coteglobal)
            paris.gainpossible=paris.coteglobal*paris.mise
            String gainpossible=df.format(paris.gainpossible).replace(',','.')
            paris.gainpossible= Double.parseDouble(gainpossible)
            paris.save(failOnError:true)
            paris.errors.allErrors
        }
    }
    def updateDetailsParisKO(String prono, Long match_id) {
        Detailsparis.executeUpdate("update Detailsparis set gain='KO' where prono <> :prono and match.id= :idmatch",[prono:prono, idmatch:match_id])
    }
}
