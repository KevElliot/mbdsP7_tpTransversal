package itu.mbds.tpt

import grails.gorm.transactions.Transactional
import org.grails.web.json.JSONArray

@Transactional
class ParisApiService {

    def placeParis(Paris paris) {
        if(paris.detailsparis.size()>0){
            paris.dateparis = new Date()
            paris.gain = 'OK'
            paris.nbmatch=paris.detailsparis.size()
            paris.nbgain=0
            paris.nbperdu=0
            def nbcotegloval =0
            paris.detailsparis.each {
                detailsparis ->
                    nbcotegloval+=detailsparis.cote
            }
            paris.coteglobal=nbcotegloval
            paris.save(failOnError:true)
            paris.errors.allErrors
        }
    }
}
